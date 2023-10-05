import React from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

function EmptyConsumePatternChart() {

    const data = {
        labels: ["empty chart"],
        datasets: [
            {
                label: '# of Votes',
                data: [1],
                backgroundColor: [
                    '#B1A8B9'
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

export default EmptyConsumePatternChart;