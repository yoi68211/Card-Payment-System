$(function() {
    $("#startDt").datepicker({
        dateFormat: 'yy-mm-dd',
        onSelect: function(selectedDate) {
            var endDate = $('#endDt');
            var startDate = $(this).datepicker('getDate');
            startDate.setDate(startDate.getDate() + 1);
            endDate.datepicker('option', 'minDate', startDate);
        }
    });

    $("#endDt").datepicker({
        dateFormat: 'yy-mm-dd',
        onSelect: function(selectedDate) {
            var startDate = $('#startDt');
            var endDate = $(this).datepicker('getDate');
            endDate.setDate(endDate.getDate() - 1);
            startDate.datepicker('option', 'maxDate', endDate);
        }
    });
});

function toggleContent() {
    var icon = document.querySelector('.fas');
    icon.classList.toggle('rotate-up');

    var content = document.getElementById("expandableContent");
    content.classList.toggle("active"); // 내용의 확장 여부를 토글
}

function setDateRange(months) {
    var endDate = new Date();
    var startDate = new Date();
    startDate.setMonth(startDate.getMonth() - months);

    // 종료일을 계산된 값으로 설정
    $('#startDt').datepicker('setDate', startDate);

    // 시작일을 오늘 날짜로 설정
    $('#endDt').datepicker('setDate', endDate);

    // 모든 버튼에 대해 비활성화 클래스를 추가
    var buttons = document.querySelectorAll('.date-range-button');
    buttons.forEach(function(button) {
        button.classList.remove('active');
    });

    // 선택된 기간에 해당하는 버튼에 active 클래스 추가
    var activeButton = document.querySelector('.date-range-button[data-months="' + months + '"]');
    if (activeButton) {
        activeButton.classList.add('active');
    }
}

function resetForm() {
    document.getElementById("form").reset();

    // 선택된 상태를 초기화하고 색상을 원래대로 변경합니다.
    var buttons = document.querySelectorAll('.status-button');
    buttons.forEach(function(button) {
        button.classList.remove('active');
    });
}

function setStatus(status) {
    document.getElementById('status').setAttribute('value', status);
}

function toggleDeleteButton() {
    var deleteButton = document.getElementById("deleteButton");
    var checkboxes = document.getElementsByName("selectedPayment");
    var isAnyCheckboxChecked = false;

    // 체크박스 중에 하나 이상이 선택되었는지 확인
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            isAnyCheckboxChecked = true;
            break;
        }
    }

    // 선택된 체크박스가 있으면 삭제 버튼 활성화, 없으면 비활성화
    deleteButton.disabled = !isAnyCheckboxChecked;
}

function getSelectedPaymentIds() {
    var selectedIds = [];
    var checkboxes = document.getElementsByName('selectedPayment');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedIds.push(checkboxes[i].value);
        }
    }
    return selectedIds;
}

function deleteSelectedPayments() {
    var selectedPaymentIds = [];
    var checkboxes = document.getElementsByName("selectedPayment");

    // 선택된 결제의 ID를 배열에 추가
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedPaymentIds.push(checkboxes[i].value);
        }
    }

    // 결제 ID를 숨은 입력 필드에 설정
    document.getElementById("paymentIds").value = selectedPaymentIds.join(",");

    // 폼의 액션을 각 결제 ID에 맞게 설정
    document.getElementById("deleteForm").action = "/delete/" + selectedPaymentIds[0];

    // 폼을 제출
    document.getElementById("deleteForm").submit();
}

function submitForm() {
    var form = document.getElementById("form");
    form.submit();
}

function changePageSize() {
    var selectedSize = document.getElementById("pageSelect").value;
    var totalItems = parseInt('${payCount}'); // 전체 아이템 수 가져오기
    var totalPages = Math.ceil(totalItems / selectedSize); // 전체 페이지 수 계산

    var url = new URL(window.location.href);
    url.searchParams.set('size', selectedSize);

    // 현재 페이지가 유효한 경우에만 새로운 페이지 번호를 계산하여 설정
    var currentPage = parseInt(getParameterByName('page')); // 현재 페이지 번호 가져오기
    if (!isNaN(currentPage) && currentPage >= 0) {
        var newPage = Math.min(currentPage, totalPages - 1); // 새로운 페이지 번호 계산
        url.searchParams.set('page', newPage);
    }

    window.location.href = url.toString();
}

// URL에서 특정 파라미터 값을 가져오는 함수
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

document.getElementById("searchInput").addEventListener("keypress", function(event) {
    if (event.keyCode === 13) { // Enter 키의 keyCode는 13입니다.
        event.preventDefault(); // 기본 동작을 막습니다.
        document.getElementById("searchForm").submit(); // 폼을 제출합니다.
    }
});

document.addEventListener("DOMContentLoaded", function() {
    var buttons = document.querySelectorAll('.status-button');

    buttons.forEach(function(button) {
        button.addEventListener("click", function() {
            var status = button.innerText;
            console.log("상태:", status);

            // 클릭된 버튼에만 .active 클래스 추가
            buttons.forEach(function(btn) {
                btn.classList.remove('active');
            });
            button.classList.add('active');
        });
    });
});