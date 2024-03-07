function validateInput(input) {
            return /^\d+$/.test(input) ? input : ""; // 숫자가 아닌 경우 빈 문자열 반환
        }

        function updateAmount(row) {
            const table = document.getElementById("product");
            let totalAmount = 0;
            for (let i = 1; i < table.rows.length - 1; i++) {
                const amountInput = table.rows[i].cells[2].querySelector("input").value;
                if (!validateInput(amountInput)) {
                    document.getElementById("totalAmount").innerHTML = "";
                }
                totalAmount += parseInt(amountInput);
            }
            if (isNaN(totalAmount)) {
                document.getElementById("totalAmount").innerHTML = "";
            } else {
                document.getElementById("totalAmount").innerHTML = totalAmount;
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



        var num = 2;

        function addRow() {
            const table = document.getElementById("product");
            var deleteRow_btn = document.getElementById("deleteRow_btn");
            const newRow = table.insertRow(num);


            const newCell1 = newRow.insertCell(0);
            /*const newCell2 = newRow.insertCell(1);*/
            const newCell2 = newRow.insertCell(1);
            const newCell3 = newRow.insertCell(2);
            const newCell4 = newRow.insertCell(3);
            const newCell5 = newRow.insertCell(4);


            const input1 = document.createElement("input");
            input1.setAttribute("type", "checkbox");
            newCell1.appendChild(input1);

            /*newCell2.innerText = num;
            newCell2.classList.add("center-text");*/

            const input2 = document.createElement("input");
            input2.setAttribute("type", "text");
            newCell2.appendChild(input2);

            const input3 = document.createElement("input");
            input3.setAttribute("type", "text");
            input3.addEventListener("input", function () {
                updateAmount(newRow);
            });
            newCell3.appendChild(input3);

            const input4 = document.createElement("input");
            input4.setAttribute("type", "text");
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
            updateAmount(newRow);
            updatePrice(newRow);
        }

        /*function deleteRow(rownum) {
            const table = document.getElementById('product');
            var deleteRow_btn = document.getElementById("deleteRow_btn");
            <!--const newRow = table.deleteRow(rownum);-->
            if (num > 2) {
                const totalRow = table.rows.length - 1;
                table.deleteRow(totalRow - 1);
                num--;
            }
            if (num == 2) {
                deleteRow_btn.disabled = true;
            }

        }*/
        function deleteRow() {
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
            if (numRows == 1) {
                deleteRow_btn.disabled = true;
            }
        }

