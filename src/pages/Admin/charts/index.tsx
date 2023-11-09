import { getRankUsingGET } from '@/services/wj-api-backend/chartController';
import { PageContainer } from '@ant-design/pro-components';
import ReactECharts from 'echarts-for-react';
import React, { useEffect, useState } from 'react';

const InterfaceChart: React.FC = () => {
  const [data, setData] = useState([]);

  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getRankUsingGET().then((res) => {
      if (res) {
        setData(res.data);
        setLoading(false);
      }
    });
  }, []);

  let ydata = data.value;
  let xdata = data.score;
  const option = {
    title: {
      text: 'API调用排行榜top10',
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
    },
    //图表位置
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true,
    },
    //X轴
    xAxis: {
      type: 'value',
      axisLine: {
        show: false,
      },
      axisTick: {
        show: false,
      },
      //不显示X轴刻度线和数字
      splitLine: { show: false },
      axisLabel: { show: false },
    },
    yAxis: {
      type: 'category',
      data: ydata,
      //升序
      inverse: true,
      splitLine: { show: false },
      axisLine: {
        show: false,
      },
      axisTick: {
        show: false,
      },
      //key和图间距
      offset: 10,
      //动画部分
      animationDuration: 300,
      animationDurationUpdate: 300,
      //key文字大小
      nameTextStyle: {
        fontSize: 5,
      },
    },
    series: [
      {
        //柱状图自动排序，排序自动让Y轴名字跟着数据动
        realtimeSort: true,
        name: '数量',
        type: 'bar',
        data: xdata,
        barWidth: 14,
        barGap: 10,
        smooth: true,
        valueAnimation: true,
        //Y轴数字显示部分
        label: {
          normal: {
            show: true,
            position: 'right',
            valueAnimation: true,
            offset: [5, -2],
            textStyle: {
              color: '#333',
              fontSize: 13,
            },
          },
        },
        itemStyle: {
          emphasis: {
            barBorderRadius: 7,
          },
          //颜色样式部分
          normal: {
            barBorderRadius: 7,
            // color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
            //   { offset: 0, color: '#3977E6' },
            //   { offset: 1, color: '#37BBF8' },
            // ]),
          },
        },
      },
    ],
    //动画部分

    animationDuration: 0,
    animationDurationUpdate: 3000,
    animationEasing: 'linear',
    animationEasingUpdate: 'linear',
  };

  return (
    <PageContainer title={'接口调用情况'}>
      <ReactECharts showLoading={loading} option={option} />
    </PageContainer>
  );
};

export default InterfaceChart;
