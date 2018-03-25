/**
 * Created by stone on 2018/3/23.
 */
window.onload = function () {
    var cardVM = new Vue({
        el:'#newCard',
        data:{
            cardId :'',
            cardType:'NORMAL',
            types:[
                {value:'NORMAL',text:'普通卡'},
                {value:'SENIOR',text:'老年卡'},
                {value:'STUDENT',text:'学生卡'},
                {value:'DISABILITY',text:'残障卡'}
            ],
            destroyId:''
        },

        methods:{
            newCard:function () {
                axios.get('/card/newcard',{
                    params:{
                        cardType:this.cardType
                    }
                })
                    .then(function (response) {
                        if (response.data.code === 0){
                            cardVM.cardId = response.data.data;
                        } else {
                            alert("系统异常，新建公交卡失败");
                        }

                    })
                    .catch(function (response) {
                        console.log(response)
                    });
            },
            destroyCard:function () {
                axios.get('/card/destroycard',{
                    params:{
                        cardId:this.destroyId
                    }
                })
                    .then(function (response) {
                        if (response.data.code === 0){
                            alert("公交卡：" + cardVM.destroyId + "   注销成功！");
                        } else {
                            alert("系统异常，注销公交卡失败");
                        }

                    })
                    .catch(function (response) {
                        console.log(response)
                    });
            }
        }
    });
};