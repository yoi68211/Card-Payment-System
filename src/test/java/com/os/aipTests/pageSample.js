// 사이드바 토글버튼
    document.getElementById('sidebar-action-icon').addEventListener('click', function() {
        document.querySelector('body').classList.toggle('collapsed');
        var sidebarNavText = document.querySelectorAll('.sidebar-nav-text');
        sidebarNavText.forEach(function(element) {
            if (element.style.display === 'none') {
                element.style.display = 'inline';
            } else {
                element.style.display = 'none';
            }
        });
    });

    // 사이드바 결제목록 토글버튼
    document.getElementById('payList-toggle').addEventListener('click', function() {
        // payList-nav 클래스를 가진 요소들을 가져옴
        var payListNavs = document.querySelectorAll('.payList-nav');

        // 각 payList-nav 요소에 대해 hidden 클래스를 추가/제거하여 페이드 인/아웃 처리
        payListNavs.forEach(function(payListNav) {
            payListNav.classList.toggle('hidden');
            payListNav.classList.toggle('visible');
        });
    });


//    // 결제금액 총합
//    let autoPayment = 0;
//    let productList = /* [[${productList}]]; */
//    for (let product of productList) {
//      autoPayment += parseInt(product.amount); // 각 항목의 amount를 정수형으로 변환하여 더함
//    }
//    console.log(autoPayment);
//    var formattedAutoPayment = numberWithCommas(autoPayment);
//    document.getElementById("autoPayment").value = formattedAutoPayment;


    // 금액 , 찍기
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    // date yyyy-MM_dd 로 변환
    function dateProcess(date){
        return date.split('T')[0];
    }
    // 전화번호 나누기
    function formatPhoneNumber(phoneNumber) {

        // 각 부분으로 나누기
        let part1 = phoneNumber.slice(0, 3);
        let part2 = phoneNumber.slice(3, 7);
        let part3 = phoneNumber.slice(7, 11);
        // 각 부분을 하나의 문자열로 합치기
        return part1 + '-' + part2 + '-' + part3;
    }
    // 전화번호 나눠서 저장
    function fillPhoneNumberField(phoneNumber) {
        let formattedPhoneNumber = formatPhoneNumber(phoneNumber);
        // 각 부분을 입력란에 채워 넣기
        document.getElementById('phone1').value = formattedPhoneNumber.slice(0, 3);
        document.getElementById('phone2').value = formattedPhoneNumber.slice(4, 8);
        document.getElementById('phone3').value = formattedPhoneNumber.slice(9, 13);
    }