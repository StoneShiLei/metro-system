<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>公共交通卡</title>
    <script src="http://cdn.jsdelivr.net/npm/vue"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
    <script src="/js/card.js"></script>
</head>
<body>
    <div id="newCard">
            注册公交卡<br>
            <label>
                <select v-model="cardType">
                    <option v-for="type in types" v-bind:value="type.value">{{ type.text }}</option>
                </select>
            </label>
            <button type="button" v-on:click="newCard">点击此处注册一张公交卡</button>
            <p>您的公交卡id为：{{ cardId }}</p>
            公交卡注销<br>
            注销公交卡：<label><input placeholder="公交卡id" type="text" v-model="destroyId"></label>
            <button type="button" v-on:click="destroyCard">确定</button>
    </div>
</body>
</html>