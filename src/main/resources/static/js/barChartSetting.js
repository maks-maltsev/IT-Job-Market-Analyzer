var lang = [[${languages}]];
var amount = [[${languagesAmount}]];

const data = {
    labels: lang,
    datasets: [{
        label: 'Amount of mentions: ',
        backgroundColor: ['#0048BA', '#B0BF1A', '#B284BE', '#DB2D43', '#C46210', '#3B7A57'],
        borderColor: 'rgb(255, 99, 132)',
        data: amount,
    }]
};

const config = {
    type: 'bar',
    data: data,
    options: {}
};