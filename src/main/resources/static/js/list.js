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
    var content = document.getElementById("form");
    if (content.hasAttribute("hidden")) {
        content.removeAttribute("hidden"); // 펼쳐짐
    } else {
        content.setAttribute("hidden", true); // 접힘
    }
}

function setDateRange(months) {
    var endDate = new Date();
    var startDate = new Date();
    startDate.setMonth(startDate.getMonth() + months);

    // 종료일을 계산된 값으로 설정
    $('#endDt').datepicker('setDate', startDate);

    // 시작일을 오늘 날짜로 설정
    $('#startDt').datepicker('setDate', endDate);
}

function resetForm() {
    document.getElementById("form").reset();
}

function toggleDeleteButton() {
    var checkboxes = document.querySelectorAll('input[name="selectedPayment"]');
    var deleteButton = document.getElementById('deleteButton');
    var anyChecked = false;

    // 모든 체크 박스를 반복하면서 체크된 것이 있는지 확인합니다.
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            anyChecked = true;
            break;
        }
    }

    // 체크된 체크 박스가 있는 경우 삭제 버튼을 활성화합니다.
    if (anyChecked) {
        deleteButton.disabled = false;
    } else {
        // 아닌 경우 삭제 버튼을 비활성화합니다.
        deleteButton.disabled = true;
    }
}

function submitForm() {
    // 조회 기능 구현
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