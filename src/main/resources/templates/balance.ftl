<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>余额查询</title>
    <script src="http://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
    <script src="/js/balance.js"></script>
</head>
<body>
    <div id="balanceInquiry">

            余额查询<br>
            <label>
                公交卡ID:<input  v-model="cardId" type="text"/>
                <button type="button" v-on:click="getBalance">查询</button>
            </label>
            <p>余额为：{{ balance }}</p>

            公交卡储值<br>
            <label>
                公交卡ID:<input v-model="rechargeId" type="text"/>
                充值金额:<input v-model="rechargeAmount" type="text" placeholder="请输入10元的整数倍"/>
                <button type="button" v-on:click="recharge">确认储值</button>
            </label>
            <p>充值后的余额为：{{ rechargeResult }}</p>
    </div>

</body>
</html>