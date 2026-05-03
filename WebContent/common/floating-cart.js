document.addEventListener('DOMContentLoaded', function () {
	var contextPath = '/' + window.location.pathname.split('/')[1];

	fetch(contextPath + '/common/floating-cart.html')
		.then(function (response) {
			return response.text();
		})
		.then(function (html) {
			document.body.insertAdjacentHTML('beforeend', html);
			startFloatingCart(contextPath);
		})
		
});

function startFloatingCart(contextPath) {
	var modal = document.querySelector('.modal');
	var cartBox = document.getElementById('shoppingbox');
	var countText = document.getElementById('place-count');
	var openButton = document.querySelector('.btn-open-modal');
	var closeButton = document.getElementById('btn-close-modal');
	var selectAllButton = document.getElementById('btn-select-all');
	var deleteButton = document.getElementById('btn-delete-selected');
	var addToScheduleButton = document.getElementById('btn-add-to-schedule');

	if (modal == null || cartBox == null || countText == null || openButton == null || closeButton == null
			|| selectAllButton == null || deleteButton == null || addToScheduleButton == null) {
		console.error('데이터가 없습니다.');
		return;
	}

	// 잠금 항목 먼저 로드 (isAlreadyInCart 중복 체크를 위해 일반 항목보다 먼저 실행)
	loadLockedItemsFromDOM(cartBox, countText);

	openButton.addEventListener('click', function () {
		openCartModal(modal);
	});

	closeButton.addEventListener('click', function () {
		closeCartModal(modal);
	});

	modal.addEventListener('click', function (event) {
		if (event.target == modal) {
			closeCartModal(modal);
		}
	});

	document.body.addEventListener('click', function (event) {
		if (event.target.className != 'add-to-cart-btn') {
			return;
		}

		event.preventDefault();

		var button = event.target;
		var placeId = button.dataset.placeId;
		var placeTitle = button.dataset.placeTitle;
		var placeType = button.dataset.placeType;

		if (placeType == null || placeType == '') {
			placeType = 'LEISURE';
		}

		if (placeId == null || placeId == '' || placeTitle == null || placeTitle == '') {
			alert('플레이스가 없습니다.');
			return;
		}

		if (isAlreadyInCart(cartBox, placeId)) {
			alert('이미 담긴 플레이스입니다.');
			return;
		}

		if (placeType == 'LEISURE' && hasLeisurePlace(cartBox)) {
			alert('레포츠는 1개만 담을 수 있습니다.');
			return;
		}

		if (placeType == 'STAY' && hasStayPlace(cartBox)) {
			alert('숙박은 1개만 담을 수 있습니다.');
			return;
		}

		saveCartToSession(contextPath, placeId, placeType);
		addCartRow(cartBox, placeId, placeTitle, placeType);
		refreshCartCount(cartBox, countText);
	});

	selectAllButton.addEventListener('click', function () {
		toggleAllCheckboxes(cartBox, selectAllButton);
	});

	deleteButton.addEventListener('click', function () {
		deleteSelectedRows(cartBox, countText, selectAllButton, contextPath);
	});

	addToScheduleButton.addEventListener('click', function () {
		var isFromScheduleMode = document.getElementById('from-schedule-mode') !== null;
		var items = cartBox.querySelectorAll('.place-item');
		var ids = [];
		for (var i = 0; i < items.length; i++) {
			if (items[i].dataset.locked === 'true') { continue; }
			var pid = items[i].dataset.placeId;
			if (pid != null && pid !== '') {
				ids.push(pid);
			}
		}

		if (ids.length == 0) {
			alert('새로 담은 장소가 없습니다.');
			return;
		}

		var body = 'placeIds=' + encodeURIComponent(ids.join(','));
		if (isFromScheduleMode) {
			var scheduleIdEl = document.getElementById('current-schedule-id');
			if (!scheduleIdEl || !scheduleIdEl.value) {
				alert('일정 정보를 찾을 수 없습니다.');
				return;
			}
			body += '&myScheduleId=' + encodeURIComponent(scheduleIdEl.value);
		}

		fetch(contextPath + '/addCartToScheduleAjax', {
			method: 'POST',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: body
		})
		.then(function (response) { return response.json(); })
		.then(function (data) {
			if (data == null) { return; }
			if (data.ok != true) {
				alert(data.message != null ? data.message : '추가에 실패했습니다.');
				return;
			}
			var added = data.added != null ? data.added : 0;
			var idSet = {};
			if (data.addedPlaceIds != null) {
				for (var k = 0; k < data.addedPlaceIds.length; k++) {
					idSet[data.addedPlaceIds[k]] = true;
				}
			}
			var rows = cartBox.querySelectorAll('.place-item');
			for (var m = 0; m < rows.length; m++) {
				var row = rows[m];
				if (row.dataset.locked !== 'true' && idSet[row.dataset.placeId]) {
					row.remove();
				}
			}

			if (isFromScheduleMode) {
				if (added == 0) {
					alert('일정에 넣은 장소가 없습니다.');
				} else {
					location.href = contextPath + '/controller?cmd=myScheduleRouteManageUI';
				}
			} else {
				refreshCartCount(cartBox, countText);
				selectAllButton.textContent = '전체 선택';
				if (added == 0) {
					alert('일정에 넣은 장소가 없습니다.');
				} else {
					alert('카트에 담긴 ' + added + '곳으로 새 일정을 만들었습니다. 내 일정에서 확인할 수 있습니다.');
				}
			}
		});
	});

	loadCartFromSession(cartBox, countText);

	var isFromScheduleMode = document.getElementById('from-schedule-mode') !== null;
	if (isFromScheduleMode) {
		addToScheduleButton.textContent = '일정에 추가';
	}
}

function openCartModal(modal) {
	modal.style.display = 'flex';
}

function closeCartModal(modal) {
	modal.style.display = 'none';
}

function refreshCartCount(cartBox, countText) {
	var placeItems = cartBox.querySelectorAll('.place-item');
	countText.textContent = placeItems.length;
}

function saveCartToSession(contextPath, placeId, placeType) {
	var url = contextPath + '/controller?cmd=addCartAction';
	var data = 'placeId=' + placeId + '&placeType=' + placeType;

	fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: data
	});
}

function addCartRow(cartBox, placeId, placeTitle, placeType) {
	var row = document.createElement('div');

	row.className = 'place-item';
	row.dataset.placeId = placeId;
	row.dataset.placeType = placeType;
	row.innerHTML = '<input type="checkbox" class="place-checkbox" /> ' + placeTitle;

	cartBox.appendChild(row);
}

function loadCartFromSession(cartBox, countText) {
	var sessionItems = document.querySelectorAll('.session-cart-item');

	for (var i = 0; i < sessionItems.length; i++) {
		var item = sessionItems[i];
		var placeId = item.dataset.placeId;
		var placeTitle = item.dataset.placeTitle;
		var placeType = item.dataset.placeType;

		addCartRow(cartBox, placeId, placeTitle, placeType);
	}

	refreshCartCount(cartBox, countText);
}

function isAlreadyInCart(cartBox, placeId) {
	var cartItems = cartBox.querySelectorAll('.place-item');

	for (var i = 0; i < cartItems.length; i++) {
		var cartItem = cartItems[i];

		if (cartItem.dataset.placeId == placeId) {
			return true;
		}
	}

	return false;
}

function hasLeisurePlace(cartBox) {
	var cartItems = cartBox.querySelectorAll('.place-item');

	for (var i = 0; i < cartItems.length; i++) {
		var cartItem = cartItems[i];
		if (cartItem.dataset.locked === 'true') { continue; }
		if (cartItem.dataset.placeType == 'LEISURE') {
			return true;
		}
	}

	return false;
}

function hasStayPlace(cartBox) {
	var cartItems = cartBox.querySelectorAll('.place-item');

	for (var i = 0; i < cartItems.length; i++) {
		var cartItem = cartItems[i];
		if (cartItem.dataset.locked === 'true') { continue; }
		if (cartItem.dataset.placeType == 'STAY') {
			return true;
		}
	}

	return false;
}

function addLockedCartRow(cartBox, placeId, placeTitle) {
	var row = document.createElement('div');
	row.className = 'place-item locked-item';
	row.dataset.placeId = placeId;
	row.dataset.locked = 'true';
	row.innerHTML = '🔒 ' + placeTitle;
	cartBox.appendChild(row);
}

function loadLockedItemsFromDOM(cartBox, countText) {
	var items = document.querySelectorAll('.locked-cart-item');
	for (var i = 0; i < items.length; i++) {
		var placeId = items[i].dataset.placeId;
		var placeTitle = items[i].dataset.placeTitle || '(장소)';
		if (placeId) {
			addLockedCartRow(cartBox, placeId, placeTitle);
		}
	}
	refreshCartCount(cartBox, countText);
}

function toggleAllCheckboxes(cartBox, selectAllButton) {
	var checkboxes = cartBox.querySelectorAll('.place-checkbox');
	var allChecked = true;

	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked == false) {
			allChecked = false;
		}
	}

	for (var j = 0; j < checkboxes.length; j++) {
		checkboxes[j].checked = !allChecked;
	}

	if (allChecked == true) {
		selectAllButton.textContent = '전체 선택';
	} else {
		selectAllButton.textContent = '전체 해제';
	}
}

function deleteSelectedRows(cartBox, countText, selectAllButton, contextPath) {
	var checkedBoxes = Array.prototype.slice.call(cartBox.querySelectorAll('.place-checkbox:checked'));

	if (checkedBoxes.length == 0) {
		alert('삭제할 항목을 선택해주세요.');
		return;
	}

	var result = confirm('선택한 ' + checkedBoxes.length + '개 항목을 삭제하시겠습니까?');
	if (result == false) {
		return;
	}

	var removeIds = [];
	for (var i = 0; i < checkedBoxes.length; i++) {
		var checkbox = checkedBoxes[i];
		var row = checkbox.parentElement;
		if (row != null && row.dataset.placeId != null && row.dataset.placeId != '') {
			removeIds.push(row.dataset.placeId);
		}
		row.remove();
	}

	refreshCartCount(cartBox, countText);
	selectAllButton.textContent = '전체 선택';

	if (removeIds.length > 0 && contextPath != null) {
		fetch(contextPath + '/removeCartItemsAjax', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body: 'placeIds=' + encodeURIComponent(removeIds.join(','))
		}).catch(function () { });
	}
}
