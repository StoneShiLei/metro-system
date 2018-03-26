/**
 * Created by stone on 2018/3/25.
 */
window.onload = function () {
    var subwayVM = new Vue({
        el: '#station',
        data: {
            start: '',
            end:'',
            subway:'',
            inBalance:'0.00',
            outBalance:'0.00',
            cardId:''
        },

        methods: {
            getSubway: function () {
                axios.get('/getsubway', {
                    params: {
                        start: this.start,
                        end: this.end
                    }
                })
                    .then(function (response) {
                        if (response.data.code === 0) {
                            subwayVM.subway = response.data.data;
                        } else {
                            alert(response.data.message);
                        }

                    })
                    .catch(function (response) {
                        console.log(response)
                    });
            },
            instation: function () {
                if (this.start === '' || this.end === ''){
                    alert("请先输入起点站和终点站")
                }
                axios.get('/in', {
                    params: {
                        cardId: this.cardId,
                        start: this.start
                    }
                })
                    .then(function (response) {
                        if (response.data.code === 0) {
                            subwayVM.inBalance = response.data.data;
                        } else {
                            alert(response.data.message);
                        }

                    })
                    .catch(function (response) {
                        console.log(response)
                    });
            },
                outstation : function () {
                    if (this.start === '' || this.end === ''){
                        alert("请先输入起点站和终点站")
                    }
                    axios.get('/out', {
                        params: {
                            cardId:this.cardId,
                            end:this.end
                        }
                    })
                        .then(function (response) {
                            if (response.data.code === 0) {
                                subwayVM.outBalance = response.data.data;
                            } else {
                                alert(response.data.message);
                            }

                        })
                        .catch(function (response) {
                            console.log(response)
                        });
                }
        }
    });
};