var today = new Date();
    var currentMonth = today.getMonth(); // 현재 월 (0부터 시작)
    var currentYear = today.getFullYear(); // 현재 년도

    // 해당 월의 첫 번째 날짜를 가져옵니다.
    var firstDayOfMonth = new Date(currentYear, currentMonth, 1);

    // 해당 월의 마지막 날짜를 가져옵니다.
    var lastDayOfMonth = new Date(currentYear, currentMonth + 1, 0);
    var totalDays = lastDayOfMonth.getDate(); // 해당 월의 총 일수

    var categories = [];
    for (var i = 1; i <= totalDays; i++) {
    categories.push(i);
    }

    var options = {
      series: [{
      name: 'Inflation', // 마우스 올렸을때 컬럼 이름
      data: [30, 20, 15, 10, 14, 14, 6, 25, 22, 15, 17, 26, 20, 11, 27, 24, 22, 24, 9, 25, 17, 15, 11, 21, 25, 16, 25, 16, 14, 20 ,30] // 날짜별 결제 내역갯수
    }],
      chart: { // 차트 전체 크기
        height: '80%',
        width: '100%',
        type: 'bar',
    },
    plotOptions: {
      bar: {
        //borderRadius: 10,
        columnWidth: '15%', // bar 너비 조절
        dataLabels: {
          position: 'top', // top, center, bottom
        },
      }
    },
    dataLabels: {
      enabled: false, // 컬럼바 위 텍스트 표시x
      offsetY: -20,
      style: {
        fontSize: '12px', // bar의 폰트 크기
        colors: ["#304758"]
      }
    },
    tooltip: {
      enabled: true, // 툴팁 활성화
      custom: function({ series, seriesIndex, dataPointIndex, w }) {
        // 툴팁 커스텀 내용을 생성합니다.
        return '<div class="custom-tooltip">' +
          '<span>' + series[seriesIndex][dataPointIndex] + '</span>' +
          '</div>';
      }
    },
    xaxis: {
      //categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
      categories: categories,
      position: 'bottom',
      axisBorder: {
        show: false
      },
      axisTicks: {
        show: false
      },
      crosshairs: {
        fill: {
          type: 'gradient',
          gradient: {
            colorFrom: '#D8E3F0',
            colorTo: '#BED1E6',
            stops: [0, 100],
            opacityFrom: 0.4,
            opacityTo: 0.5,
          }
        }
      },

    },
    yaxis: {
      axisBorder: {
        show: false
      },
      axisTicks: {
        show: false,
      },
      labels: {
        show: false,
        formatter: function (val) {
          return val + "%";
        }
      }

    },
    title: {
      //text: '결제 처리 통계', // 차트 밑 텍스트
      floating: true,
      offsetY: -10,
      offsetX: 30,
      align: 'left',
      style: {
        // <color></color>: '#444'
      }
    },
    legend: {
    position: 'right', // 범례 위치 설정
    offsetY: 40, // 범례의 Y축 오프셋 설정
    }
    };

var chart = new ApexCharts(document.querySelector("#chart"), options);
chart.render();

