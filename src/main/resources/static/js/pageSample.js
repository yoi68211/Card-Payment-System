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

    // 전화번호 합치기
    function combinePhoneNumber(phone1, phone2, phone3) {
        // 각 부분을 하나의 문자열로 합치기
        return phone1 + phone2 + phone3;
    }

    // 전화번호 합쳐서 저장
    function storeCombinedPhoneNumber() {
        let phone1 = document.getElementById('phone1').value;
        let phone2 = document.getElementById('phone2').value;
        let phone3 = document.getElementById('phone3').value;
        let combinedPhoneNumber = combinePhoneNumber(phone1, phone2, phone3);
        // 합쳐진 전화번호를 변수에 저장하거나 다른 처리를 수행할 수 있음
        console.log('합친 전화번호', combinedPhoneNumber);
    }

   const titleRex = /^.{0,100}$/; // 제목 정규식 최대 100 글자 not null
   const memoRex = /^.{0,300}$/; // 메모 정규식 최대 300글자 null 가능
   const intRex= /^[0-9]+$/; // 연락처 수량 등 숫자만 입력 null 가능
   const nameRex = /^.{0,16}$/; // 이름 정규식 최대 16글자 not null
   // 이메일 정규식 이메일 형식만 가능 not null
   const emailRex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
   // 비밀번호 정규식 13글자 영어+숫자+대문자+특수문자not null
   const pwdRex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[A-Z])(?=.*[!@#$%^&*()\-_=+\\|[\]{};:'",.<>/?]).{13,}$/;
   const phoneRex = /^[0-9]{10,13}$/; // 연락처 정규식 숫자만 10 ~ 13 글자 가능
   const proNameRex = /^[\p{L}\d\s]{0,100}$/u; // 상품명 정규식 특수기호 없이 최대 100글자
