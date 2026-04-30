document.addEventListener('DOMContentLoaded', function () {
    var ctx = '/' + window.location.pathname.split('/')[1];
    function $(id) {
        return document.getElementById(id);
    }

    fetch(ctx + '/common/floating-cart.html')
        .then(function (r) {
            return r.text();
        })
        .then(function (html) {
            document.body.insertAdjacentHTML('beforeend', html);

            var modal = document.querySelector('.modal');
            var box = $('shoppingbox');
            var cnt = $('place-count');
            var btnOpen = document.querySelector('.btn-open-modal');
            var btnClose = $('btn-close-modal');
            var btnAll = $('btn-select-all');
            var btnDel = $('btn-delete-selected');
            if (!modal || !box || !cnt || !btnOpen || !btnClose || !btnAll || !btnDel) {
                console.error('플로팅 카트: 플레이스 없음');
                return;
            }

            function setModal(open) {
                modal.style.display = open ? 'flex' : 'none';
            }
            function refreshCount() {
                cnt.textContent = box.querySelectorAll('.place-item').length;
            }

            btnOpen.addEventListener('click', function () {
                setModal(true);
            });
            btnClose.addEventListener('click', function () {
                setModal(false);
            });
            modal.addEventListener('click', function (e) {
                if (e.target === modal) setModal(false);
            });

            document.body.addEventListener('click', function (e) {
                var btn = e.target.closest && e.target.closest('.add-to-cart-btn');
                if (!btn) return;
                e.preventDefault();
                var placeId = btn.dataset.placeId;
                var placeTitle = btn.dataset.placeTitle;
                var placeType = btn.dataset.placeType || 'LEISURE';
                if (!placeId || !placeTitle) {
                    alert('플레이스가 없습니다..');
                    return;
                }
                if (box.querySelector('.place-item[data-place-id="' + placeId + '"]')) {
                    alert('이미 담긴 플레이스입니다.');
                    return;
                }
                if (placeType === 'LEISURE' && box.querySelectorAll('.place-item[data-place-type="LEISURE"]').length >= 1) {
                    alert('🛑레포츠는 1개만 담을 수 있습니다.');
                    return;
                }
                var row = document.createElement('div');
                row.className = 'place-item';
                row.dataset.placeId = placeId;
                row.dataset.placeType = placeType;
                row.innerHTML = '<input type="checkbox" class="place-checkbox" /> ' + placeTitle;
                box.appendChild(row);
                refreshCount();
            });

            btnAll.addEventListener('click', function () {
                var cbs = box.querySelectorAll('.place-checkbox');
                var list = Array.prototype.slice.call(cbs);
                var allOn = list.every(function (c) {
                    return c.checked;
                });
                list.forEach(function (c) {
                    c.checked = !allOn;
                });
                btnAll.textContent = allOn ? '전체 선택' : '전체 해제';
            });

            btnDel.addEventListener('click', function () {
                var picked = box.querySelectorAll('.place-checkbox:checked');
                if (!picked.length) {
                    alert('삭제할 항목을 선택해주세요.');
                    return;
                }
                if (!confirm('선택한 ' + picked.length + '개 항목을 삭제하시겠습니까?')) return;
                Array.prototype.forEach.call(picked, function (c) {
                    c.closest('.place-item').remove();
                });
                refreshCount();
                btnAll.textContent = '전체 선택';
            });

            refreshCount();
        })
        .catch(function (err) {
            console.error('플로팅 박스 로딩 실패:', err);
        });
});
