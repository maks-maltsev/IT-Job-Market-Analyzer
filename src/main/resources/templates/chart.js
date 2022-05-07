
    var lang = [[${languages}]];
    var amount = [[${languageAmount}]];



    const data = {
    labels: lang,
    datasets: [{
    label: 'Top Programming Languages',
    backgroundColor: 'rgb(18,215,57)',
    borderColor: [red, blue, violet, black, brown],
    data: amount,
}]
};

    const config = {
    type: 'bar',
    data: data,
    options: {}
};

    const myChart = new Chart(
    document.getElementById('myChart'),
    config
    );