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
<div id="station">
    选择起点站和终点站<br>
    <label>
        起点站:<input type="text" v-model="start"/>
        终点站:<input type="text" v-model="end"/>
        <button type="button" v-on:click="getSubway">确定</button>
    </label>
    <p>路径：{{ subway }}</p>
    <br>
    <br>
    <label>
        公交卡id:<input type="text" v-model="cardId"/><br>
    </label>
    <button type="button" v-on:click="instation">入站</button><br>
    <p>入站时余额：{{ inBalance }}</p>
    <button type="button" v-on:click="outstation">出站</button><br>
    <p>出站时余额：{{ outBalance }}</p>
</div>
</body>
</html>