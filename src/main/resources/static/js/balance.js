/**
 * Created by stone on 2018/3/21.
 */

window.onload = function () {
    var balanceInquiryVM = new Vue({
        el:'#balanceInquiry',
        data:{
            cardId :'',
            balance: '0.00',
            rechargeId:'',
            rechargeAmount:'',
            rechargeResult:'0.00'
        },

        methods:{
            getBalance:function () {
                axios.get('/balance/getbalance', {
                    params: {
                        cardId: this.cardId
                    }
                })
                    .then(function (response) {
                        if (response.data.code === 0){
                            balanceInquiryVM.balance = response.data.data;
                        } else {
                            alert(response.data.message);
                        }

                    })
                    .catch(function (response) {
                        console.log(response)
                    });
            },
            recharge:function () {
                if (this.rechargeAmount <= 0 || this.rechargeAmount%10 !== 0){
                    alert("储值金额请输入10元的整数倍");
                    return;
                }
                axios.get('/balance/recharge', {
                    params: {
                        cardId: this.rechargeId,
                        amount:this.rechargeAmount
                    }
                })
                    .then(function (response) {
                        if (response.data.code === 0){
                            balanceInquiryVM.rechargeResult = response.data.data;
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
