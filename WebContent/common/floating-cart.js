		document.addEventListener('DOMContentLoaded', () => {

    // ✅ floating-cart.html을 불러와서 body 끝에 삽입
    fetch('common/floating-cart.html')
        .then(res => res.text())
        .then(html => {
            document.body.insertAdjacentHTML('beforeend', html);
            initFloatingCart();
        })
        .catch(err => console.error('플로팅 박스 로딩 실패:', err));

    function initFloatingCart() {
        const modal = document.querySelector('.modal');
        const btnOpen = document.querySelector('.btn-open-modal');
        const btnClose = document.getElementById('btn-close-modal');
        const btnSelectAll = document.getElementById('btn-select-all');
        const btnDelete = document.getElementById('btn-delete-selected');
        const shoppingbox = document.getElementById('shoppingbox');
        const placeCount = document.getElementById('place-count');

        // 모달 열기
        btnOpen.addEventListener('click', () => {
            modal.style.display = 'flex';
        });

        // 모달 닫기
        btnClose.addEventListener('click', () => {
            modal.style.display = 'none';
        });

        // 배경 클릭 시 닫기
        modal.addEventListener('click', (e) => {
            if (e.target === modal) modal.style.display = 'none';
        });

        // 개수 업데이트
        function updateCount() {
            const items = shoppingbox.querySelectorAll('.place-item');
            placeCount.textContent = items.length;
        }

        // 전체 선택 / 해제
        btnSelectAll.addEventListener('click', () => {
            const checkboxes = shoppingbox.querySelectorAll('.place-checkbox');
            const allChecked = Array.from(checkboxes).every(cb => cb.checked);
            checkboxes.forEach(cb => cb.checked = !allChecked);
            btnSelectAll.textContent = allChecked ? '전체 선택' : '전체 해제';
        });

        // 선택 삭제
        btnDelete.addEventListener('click', () => {
            const checked = shoppingbox.querySelectorAll('.place-checkbox:checked');
            if (checked.length === 0) {
                alert('삭제할 항목을 선택해주세요.');
                return;
            }
            if (confirm(`선택한 ${checked.length}개 항목을 삭제하시겠습니까?`)) {
                checked.forEach(cb => cb.closest('.place-item').remove());
                updateCount();
                btnSelectAll.textContent = '전체 선택';
            }
        });
    }
});
