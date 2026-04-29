document.addEventListener('DOMContentLoaded', () => {

    const contextPath = '/' + window.location.pathname.split('/')[1];

    // ✅ floating-cart.html을 불러와서 body 끝에 삽입
    fetch(contextPath + '/common/floating-cart.html')
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

        if (!modal || !btnOpen || !btnClose || !btnSelectAll || !btnDelete || !shoppingbox || !placeCount) {
            console.error('플로팅 카트 초기화 실패: 필수 요소를 찾지 못했습니다.');
            return;
        }

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

        // 홈 카드의 "담기" 버튼을 카트와 연결
        function bindAddToCartButtons() {
            const addButtons = document.querySelectorAll('.add-to-cart-btn');
            addButtons.forEach((btn) => {
                btn.addEventListener('click', (e) => {
                    e.preventDefault();
                    const placeId = btn.dataset.placeId;
                    const placeTitle = btn.dataset.placeTitle;
                    const placeType = btn.dataset.placeType || 'LEISURE';

                    if (!placeId || !placeTitle) {
                        alert('담기 대상 정보가 올바르지 않습니다.');
                        return;
                    }

                    // 같은 장소 중복 담기 방지
                    const existsSame = shoppingbox.querySelector(`.place-item[data-place-id="${placeId}"]`);
                    if (existsSame) {
                        alert('이미 담긴 플레이스입니다.');
                        return;
                    }

                    // 레포츠는 최대 1개만 허용
                    if (placeType === 'LEISURE') {
                        const leisureCount = shoppingbox.querySelectorAll('.place-item[data-place-type="LEISURE"]').length;
                        if (leisureCount >= 1) {
                            alert('레포츠는 1개만 담을 수 있습니다.');
                            return;
                        }
                    }

                    const item = document.createElement('div');
                    item.className = 'place-item';
                    item.dataset.placeId = placeId;
                    item.dataset.placeType = placeType;
                    item.innerHTML = `<input type="checkbox" class="place-checkbox" /> ${placeTitle}`;
                    shoppingbox.appendChild(item);
                    updateCount();
                });
            });
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

        bindAddToCartButtons();
        updateCount();
    }
});
