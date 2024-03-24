



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

            // product id 체크박스 value 리스트
            // product table 리스트
            // 행 삭제시 다른 삭제된 체크박스 id 배열에 저장

            let f = document.getElementById("basic-form");
                let param = {
                    customerId : f.customerId.value,
                    customerName : f.customerName.value,
                    customerEmail : f.customerEmail.value,
                    paymentTitle : f.paymentTitle.value,
                    paymentId : f.paymentId.value
                }
            //


            fetch('/payDetailEdit', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(param)
                }).then(response => {
                if (response.ok) {
                    // 요청이 성공적으로 처리되었을 때의 동작
                    console.log('Request succeeded');
                    alert("수정되었습니다.");
                    console.log(response);
                } else {
                    // 요청이 실패했을 때의 동작
                    console.error('Request failed');
                    alert("실패했습니다.");
                }
                }).catch(error => {
                    console.error('Error:', error);
                    alert("알수없는 에러.");
            });


            // update aJax 요청 실행

            // 행 삭제가 있다면 product 는 delete 실행
            // 행 삭제시 product_id 배열에 저장하고 백에서 해당 변수 비어있지 않다면 delete 실헹

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
        productNameCell.innerHTML = '<label><input type="text" name="productTitle" class="editable" ></label>';
        productTotalItemsCell.innerHTML = '<label><input type="number" name="TotalItem" class="editable" ></label>';
        productPriceCell.innerHTML = '<label><input type="number" name="productPrice" class="editable" ></label>';
        productAmountCell.innerHTML = '<label><input type="number" name="productAmount" class="editable" ></label>';

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
