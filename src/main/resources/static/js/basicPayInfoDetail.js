



    // 편집버튼 클릭
    let isEditing = false;
    function toggleEdit() {

        if(!isEditing){
            // 편집 모드
            // 클래스 요소들의 disabled 속성 설정
            const inputs = document.querySelectorAll('.basic-main .editable');
            inputs.forEach(input => {
                input.disabled = false; // 편집 모드에서는 모든 input 비활성화
            });

            // 편집 버튼 텍스트 변경
            const editButton = document.querySelector('.basic-header button:nth-of-type(1)');
            editButton.textContent = '저장';

            // 테두리 보이게 설정
            const tableInputs = document.querySelectorAll('.basic-footer-table input');
            tableInputs.forEach(input => {
                input.style.border = '1px solid black';
                input.style.outline = '1px solid black';
                input.style.backgroundColor = 'white';
            });

            // 행 추가, 삭제 버튼 비활성화
            const addButton = document.querySelector('.basic-footer-header button:nth-of-type(1)');
            const deleteButton = document.querySelector('.basic-footer-header button:nth-of-type(2)');
            addButton.disabled = false;
            deleteButton.disabled = false;

            isEditing = true;
        } else {
            // 저장 모드
            const result = confirm("수정하시겠습니까?");

            // 클래스 요소들의 disabled 속성 설정
                        const inputs = document.querySelectorAll('.basic-main .editable');
                        inputs.forEach(input => {
                            input.disabled = true; // 저장 모드에서는 모든 input 활성화
                        });

                        // 편집 버튼 텍스트 변경
                        const editButton = document.querySelector('.basic-header button:nth-of-type(1)');
                        editButton.textContent = '편집';

                        // 테두리 안보이게 설정
                        const tableInputs = document.querySelectorAll('.basic-footer-table input');
                        tableInputs.forEach(input => {
                            input.style.border = 'none';
                            input.style.outline = 'none';
                            input.style.backgroundColor = 'white';
                        });


                        // 행 추가, 삭제 버튼 비활성화
                        const addButton = document.querySelector('.basic-footer-header button:nth-of-type(1)');
                        const deleteButton = document.querySelector('.basic-footer-header button:nth-of-type(2)');
                        addButton.disabled = true;
                        deleteButton.disabled = true;

                        isEditing = false;


            if(!result){
                alert("취소되었습니다.");

                return;
            }
            alert("수정되었습니다.");
            // update aJax 요청 실행

            // 행 삭제가 있다면 product 는 delete 실행


        }
    }; // 편집 버튼 end

    // 행추가 버튼
    function fnAddTable() {
        // 테이블 요소 가져오기
        const tableBody = document.querySelector('.basic-footer-table tbody');

        // 새로운 행(tr) 요소 생성
        const newRow = document.createElement('tr');

        // 새로운 행에 필요한 셀(td) 요소들 생성
        const checkboxCell = document.createElement('td');
        const noCell = document.createElement('td');
        const productNameCell = document.createElement('td');
        const productTotalItemsCell = document.createElement('td');
        const productPriceCell = document.createElement('td');
        const productAmountCell = document.createElement('td');

        // 각 셀에 input 요소 추가
        checkboxCell.innerHTML = '<input type="checkbox" >';
        productNameCell.innerHTML = '<label><input type="text" class="editable" ></label>';
        productTotalItemsCell.innerHTML = '<label><input type="number" class="editable" ></label>';
        productPriceCell.innerHTML = '<label><input type="number" class="editable" ></label>';
        productAmountCell.innerHTML = '<label><input type="number" class="editable" ></label>';

        // 새로운 행에 셀 추가
        newRow.appendChild(checkboxCell);
        newRow.appendChild(noCell);
        newRow.appendChild(productNameCell);
        newRow.appendChild(productTotalItemsCell);
        newRow.appendChild(productPriceCell);
        newRow.appendChild(productAmountCell);

        // 마지막 행 가져오기
        const lastRow = tableBody.querySelector('tr:last-of-type');

        // 마지막 행이 있으면
        if (lastRow) {
            // 현재 행의 개수 확인
            const rowCount = tableBody.querySelectorAll('tr').length;

            // 현재 행의 개수에 1을 더하여 새로운 행의 번호로 설정
            noCell.innerText = rowCount.toString();

            // 마지막 행 이전에 새로운 행 추가
            lastRow.insertAdjacentElement('beforebegin', newRow);
        } else {
            // 마지막 행이 없으면 테이블 첫 번째에 새로운 행 추가
            tableBody.appendChild(newRow);
        }
    }
    // 헹 삭제 버튼
    function fnDelTable(){



    }
