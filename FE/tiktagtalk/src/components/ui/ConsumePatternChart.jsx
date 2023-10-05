import React from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

function ConsumePatternChart({ tagList }) {
    let etc = 100.0;
    for(let i = 0; i < 4; i++) {
        etc -= tagList[i].percent;
    }

    const data = {
        labels: [tagList[0].name, tagList[1].name, tagList[2].name, tagList[3].name, "기타"],
        datasets: [
            {
                label: '# of Votes',
                data: [tagList[0].percent, tagList[1].percent, tagList[2].percent, tagList[3].percent, etc],
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 206, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(153, 102, 255)'
                ],
                borderWidth: 0,
            },
        ],
    }

    return (
        <Doughnut
            data={data}
            options={{
                plugins: {
                    legend: {
                        display: false
                    }
                },
                cutout: '75%'
            }}
        />
    )
}

export default ConsumePatternChart;