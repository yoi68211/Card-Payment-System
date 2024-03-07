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
