const userCode = "imp54148822";
//const IMP = window.IMP; // 생략 가능
IMP.init("imp54148822");

function fnBasicPay() {
    let f = document.getElementById("basic-form");
    let customerPhone = document.getElementById("customerPhone").value;
    let customerAddress = document.getElementById("customerAddress").value;
    let totalAmount = document.getElementById("totalAmount").value;
    let payStatus = document.getElementById("payStatus").innerText;
    if(document.getElementById("payStatus").innerText == '결제완료'){
        alert("이미 결제가 완료되었습니다.");

        return;
    }


    let param = {
        paymentId: f.paymentId.value,
    }
    IMP.request_pay({
        pg: 'html5_inicis.INIpayTest', //테스트 시 html5_inicis.INIpayTest 기재
        pay_method: 'card', // 결제수단 구분코드
        merchant_uid: "order_no_0" + f.paymentId.value, // 매번 고유하게 채번되어야함
        name: f.paymentTitle.value, // 결제 제목
        amount: totalAmount, // 결제 금액
        buyer_email: f.customerEmail.value, // 구매자 이메일
        buyer_name: f.customerName.value, // 구매자 이름
        buyer_tel: customerPhone, //구매자 연락처 필수 값
        buyer_addr: customerAddress, // 구매자 주소
        buyer_postcode: '123-456', // 우편 번호
        m_redirect_url: '{모바일에서 결제 완료 후 리디렉션 될 URL}', // 모바일에서 결제시
        escrow: true, //에스크로 결제인 경우 설정
        vbank_due: 'YYYYMMDD', // 입금 기한 설정??
        //        bypass: {
        //            acceptmethod : "noeasypay", // 간편결제 버튼을 통합결제창에서 제외(PC)
        //            P_RESERVED: "noeasypay=Y", // 간편결제 버튼을 통합결제창에서 제외(모바일)
        //            acceptmethod: 'cardpoint', // 카드포인트 사용시 설정(PC)
        //            P_RESERVED: 'cp_yn=Y', // 카드포인트 사용시 설정(모바일)
        //        },
        period: {
            from: "20200101", //YYYYMMDD
            to: "20201231" //YYYYMMDD
        }
    }, function(rsp) { // callback 로직
        //* ...중략... *//
        if (rsp.success) {
            alert("결제 성공");
            console.log("결제 고유번호" + rsp.imp_uid);
            console.log("주문번호" + rsp.merchant_uid);


            fetch('/basicPayPaid?id='+f.paymentId.value, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(response => {
                if (response.ok) {
                    // 요청이 성공적으로 처리되었을 때의 동작
                    alert("결제상태 update");
                    location.reload(true);
                } else {
                    // 요청이 실패했을 때의 동작
                    alert("실패했습니다.");
                }
            }).catch(error => {
                console.error('Error:', error);
                alert("알수없는 에러.");
            });

        } else {
            //alert("결제에 실패하였습니다. 에러 내용: \n" + rsp.error_msg);
            fetch('/basicPayError?id='+f.paymentId.value, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(response => {
                if (response.ok) {
                    // 요청이 성공적으로 처리되었을 때의 동작
                    location.reload(true);
                } else {
                    // 요청이 실패했을 때의 동작
                    alert("실패했습니다.");
                }
            }).catch(error => {
                console.error('Error:', error);
                alert("알수없는 에러.");
            });
        }
    });


}