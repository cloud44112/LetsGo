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
		.catch(function (err) {
			console.error('플로팅 박스 로딩 실패:', err);
		});
});

function startFloatingCart(contextPath) {
	var modal = document.querySelector('.modal');
	var cartBox = document.getElementById('shoppingbox');
	var countText = document.getElementById('place-count');
	var openButton = document.querySelector('.btn-open-modal');
	var closeButton = document.getElementById('btn-close-modal');
	var selectAllButton = document.getElementById('btn-select-all');
	var deleteButton = document.getElementById('btn-delete-selected');

	if (modal == null || cartBox == null || countText == null || openButton == null || closeButton == null
			|| selectAllButton == null || deleteButton == null) {
		console.error('데이터가 없습니다.');
		return;
	}

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

		saveCartToSession(contextPath, placeId, placeType);
		addCartRow(cartBox, placeId, placeTitle, placeType);
		refreshCartCount(cartBox, countText);
	});

	selectAllButton.addEventListener('click', function () {
		toggleAllCheckboxes(cartBox, selectAllButton);
	});

	deleteButton.addEventListener('click', function () {
		deleteSelectedRows(cartBox, countText, selectAllButton);
	});

	loadCartFromSession(cartBox, countText);
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

		if (cartItem.dataset.placeType == 'LEISURE') {
			return true;
		}
	}

	return false;
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

function deleteSelectedRows(cartBox, countText, selectAllButton) {
	var checkedBoxes = cartBox.querySelectorAll('.place-checkbox:checked');

	if (checkedBoxes.length == 0) {
		alert('삭제할 항목을 선택해주세요.');
		return;
	}

	var result = confirm('선택한 ' + checkedBoxes.length + '개 항목을 삭제하시겠습니까?');
	if (result == false) {
		return;
	}

	for (var i = 0; i < checkedBoxes.length; i++) {
		var checkbox = checkedBoxes[i];
		var row = checkbox.parentElement;
		row.remove();
	}

	refreshCartCount(cartBox, countText);
	selectAllButton.textContent = '전체 선택';
}
