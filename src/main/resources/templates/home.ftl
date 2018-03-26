<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>地铁系统</title>
    <script src="http://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
    <script src="/js/home.js"></script>
</head>
<body>
<a href="/card/manage" target="_blank">注册公交卡</a>
<a href="/balance/home" target="_blank">充值</a>
<div id="station">
    选择起点站和终点站<br>
    <label>
        起点站:<input type="text" v-model="start"/>
        终点站:<input type="text" v-model="end"/>
        <button type="button" v-on:click="getSubway">确定</button>
    </label>
    <p>路径：{{ subway }}</p>
    <br>
    <img src="IMG_3316.PNG">
    <br>
    <label>
        公交卡id:<input type="text" v-model="cardId"/><br>
    </label>
    <button type="button" v-on:click="instation">入站</button><br>
    <p>入站时余额：{{ inBalance }}</p>
    <button type="button" v-on:click="outstation">出站</button><br>
    <p>出站时余额：{{ outBalance }}</p>

    <br>
    <br>
    <div>
        <p>
            计价规则：<br>
            •	当进站点和出站点不同时，采用阶梯计价原则：从进站点开始计算，出站点为前五站的收费1元，第六站到第十站的收费2元，以此类推之后每隔五站递增1元。<br>
            •	进站记录和出站记录需成对匹配，即进站记录的前一条若同为进站记录时，前一条进站记录以统一收费5元清算，出站记录同理。<br>
            优惠政策：<br>
            •	普通卡按计价规则计费，无优惠。<br>
            •	学生卡按计价规则计费，除第三、第四条规则外，一律五折优惠。<br>
            •	老年卡按计价规则计费，一律免费。<br>
            •	残障卡按计价规则计费，除第三、第四条规则外，每天可免费乘坐两次，从第三次开始一律三折优惠。(待完成)<br>

        </p>
    </div>
</div>
</body>
</html>