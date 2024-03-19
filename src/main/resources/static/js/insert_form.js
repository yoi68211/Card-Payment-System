window.addEventListener('DOMContentLoaded', function() {
        // 현재 시간을 가져오는 함수
        function getCurrentTime() {
            const now = new Date();
            const year = now.getFullYear();
            const month = String(now.getMonth() + 1).padStart(2, '0');
            const day = String(now.getDate()).padStart(2, '0');
            const currentTime = `${year}-${month}-${day}`;
            return currentTime;
        }

        // createTime input 태그를 찾아 현재 시간을 설정
        const createTimeInput = document.getElementById('paymentCreateTime');
        if (createTimeInput) {
            createTimeInput.value = getCurrentTime();
        }
    });

function validateInput(input) {
            return /^\d+$/.test(input) ? input : ""; // 숫자가 아닌 경우 빈 문자열 반환
        }

        function updatetotalItems(row) {
            const table = document.getElementById("product");
            let totalAmount = 0;
            for (let i = 1; i < table.rows.length - 1; i++) {
                const amountInput = table.rows[i].cells[2].querySelector("input").value;
                if (!validateInput(amountInput)) {
                    document.getElementById("totaltotalItems").innerHTML = "";
                }
                totalAmount += parseInt(amountInput);
            }
            if (isNaN(totalAmount)) {
                document.getElementById("totaltotalItems").innerHTML = "";
            } else {
                document.getElementById("totaltotalItems").innerHTML = totalAmount;
            }

            const amountInput = row.cells[2].querySelector("input").value;
            const priceInput = row.cells[3].querySelector("input").value;
            const totalInput = row.cells[4];
            if (validateInput(amountInput)) {
                const amount = parseInt(amountInput); // 수량 값
                const price = parseInt(priceInput); // 단가 값
                const total = amount * price;
                if (isNaN(total)) {
                    totalInput.innerHTML = "";
                    return;
                }
                totalInput.innerHTML = total;
                const table = document.getElementById("product");
                let ttotal = 0;
                for (let i = 1; i < table.rows.length - 1; i++) {
                    const amountInput = table.rows[i].cells[4].innerHTML;
                    if (!isNaN(amountInput)) {
                        ttotal += parseInt(amountInput);
                    }
                }
                if(isNaN(ttotal)){
                    document.getElementById("final").innerHTML = "";
                }else{
                    document.getElementById("final").innerHTML = ttotal;
                }

            } else {
                totalInput.innerHTML = "";
                document.getElementById("final").innerHTML = "";
                return;
            }

        }

        function updatePrice(row) {

            const table = document.getElementById("product");
            let totalPrice = 0;
            for (let i = 1; i < table.rows.length - 1; i++) {
                const priceInput = table.rows[i].cells[3].querySelector("input").value;
                if (!validateInput(priceInput)) {
                    document.getElementById("totalPrice").innerHTML = "";
                }
                totalPrice += parseInt(priceInput);
            }
            if (isNaN(totalPrice)) {
                document.getElementById("totalPrice").innerHTML = "";
            } else {
                document.getElementById("totalPrice").innerHTML = totalPrice;
            }

            const amountInput = row.cells[2].querySelector("input").value;
            const priceInput = row.cells[3].querySelector("input").value;
            const totalInput = row.cells[4];
            if (validateInput(priceInput)) {
                const amount = parseInt(amountInput); // 수량 값
                const price = parseInt(priceInput); // 단가 값
                const total = amount * price;
                if (isNaN(total)) {
                    totalInput.innerHTML = "";
                    return;
                }
                totalInput.innerHTML = total;
                const table = document.getElementById("product");
                let ttotal = 0;
                for (let i = 1; i < table.rows.length - 1; i++) {
                    const amountInput = table.rows[i].cells[4].innerHTML;
                    if (!isNaN(amountInput)) {
                        ttotal += parseInt(amountInput);
                    }
                }
                if(isNaN(ttotal)){
                    document.getElementById("final").innerHTML = "";
                }else{
                    document.getElementById("final").innerHTML = ttotal;
                }
            } else {
                totalInput.innerHTML = "";
                document.getElementById("final").innerHTML = "";
                return;
            }

        }



        let num = 2;

                function addRow() {

                    const table = document.getElementById("product");
                    var deleteRow_btn = document.getElementById("deleteRow_btn");
                    const newRow = table.insertRow(num);


                    const newCell1 = newRow.insertCell(0);
                    const newCell2 = newRow.insertCell(1);
                    const newCell3 = newRow.insertCell(2);
                    const newCell4 = newRow.insertCell(3);
                    const newCell5 = newRow.insertCell(4);


                    const input1 = document.createElement("input");
                    input1.setAttribute("type", "checkbox");
                    newCell1.appendChild(input1);

                    const input2 = document.createElement("input");
                    input2.setAttribute("type", "text");
                    input2.setAttribute("name", "productName");
                    newCell2.appendChild(input2);

                    const input3 = document.createElement("input");
                    input3.setAttribute("type", "text");
                    input3.setAttribute("name", "productTotalItems");
                    input3.addEventListener("input", function () {
                        updatetotalItems(newRow);
                    });
                    newCell3.appendChild(input3);

                    const input4 = document.createElement("input");
                    input4.setAttribute("type", "text");
                    input4.setAttribute("name", "productPrice");
                    input4.addEventListener("input", function () {
                        updatePrice(newRow);
                    });
                    newCell4.appendChild(input4);

                    newCell5.innerText = "";
                    newCell5.classList.add("center-text");

                    num++;

                    deleteRow_btn.disabled = false;
                    document.getElementById("final").innerHTML = "";
                    // 새로운 행 추가 후에도 1번 행에 대해 calculateAmount 호출
                    updatetotalItems(newRow);
                    updatePrice(newRow);
                }

        function deleteRow(rownum) {
            const table = document.getElementById('product');
            var deleteRow_btn = document.getElementById("deleteRow_btn");
            let numRows = table.rows.length - 2; // 첫 행과 마지막 행은 무시하기 위해 -2
            let deletedRows = 0; // 삭제된 행의 개수를 저장하는 변수 추가
            for (let i = numRows; i > 0; i--) {
                const checkbox = table.rows[i].cells[0].querySelector("input[type='checkbox']");
                if (checkbox.checked) {
                    table.deleteRow(i);
                    numRows--;
                    deletedRows++; // 삭제된 행의 개수 증가
                }
            }
            num -= deletedRows; // 삭제된 행의 개수만큼 num에서 빼기
            if (numRows == 0) {
                deleteRow_btn.disabled = true;
            }
        }

        function send() {
                    var f = document.getElementById('paymentForm');
                    var createTime = f.paymentCreateTime.value;
                    var name = f.customerName.value;
                    var email = f.customerEmail.value;
                    var phone = f.customerPhone.value;
                    var address = f.customerAddress.value;
                    var title = f.paymentTitle.value;
                    var type = f.paymentType.checked ? "auto" : "basic";
                    var bizTo = f.paymentBizTo.value;
                    var month = f.autoMonth.value == "다음달" ? 1 : 2;
                    var autoDate = f.autoDate.value;
                    var pay = f.autoPay.value;

                    var productRows = document.querySelectorAll("#product tbody tr");
                    if (productRows.length > 2 && productRows[1].querySelector("input[type='checkbox']").checked) {
                            alert("결제 물품에 등록된 전체 물품을 결제합니다. 체크박스를 해제해 주세요");
                            return;
                    }

                    var productList = [];
                    for (var i = 1; i < productRows.length - 1; i++) {
                        var productName = productRows[i].querySelector("input[name='productName']").value;
                        var totalItems = productRows[i].querySelector("input[name='productTotalItems']").value;
                        var price = productRows[i].querySelector("input[name='productPrice']").value;
                        var productAmount = productRows[i].querySelector("#productAmount").innerText;
                        productList.push({ productName: productName, productTotalItems: totalItems, productPrice: price, productAmount: productAmount });
                    }

                    var data = {
                        paymentCreateTime: createTime,
                        customerName: name,
                        customerEmail: email,
                        customerPhone: phone,
                        customerAddress: address,
                        paymentTitle: title,
                        paymentType: type,
                        paymentBizTo: bizTo,
                        autoMonth: month,
                        autoDate: autoDate,
                        autoPay: autoPay,
                        productList: productList
                    };

                    // JSON 데이터를 서버에 전송
                    fetch('/insert', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(data)
                    }).then(response => {
                        if (response.ok) {
                            // 요청이 성공적으로 처리되었을 때의 동작
                            console.log('Request succeeded');
                        } else {
                            // 요청이 실패했을 때의 동작
                            console.error('Request failed');
                        }
                    }).catch(error => {
                        console.error('Error:', error);
                    });
                }

                function toggleAllCheckboxes() {
                    var masterCheckbox = document.getElementById('masterCheckbox');
                    var checkboxes = document.querySelectorAll("#product input[type='checkbox']");
                    checkboxes.forEach(function(checkbox) {
                        checkbox.checked = masterCheckbox.checked;
                    });
                }

                window.addEventListener('DOMContentLoaded', function() {
                    document.getElementById('masterCheckbox').addEventListener('click', toggleAllCheckboxes);
                });

