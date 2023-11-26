<template>
    <img :src="qrcode" />
    <Totp />
</template>

<script>
import Totp from './components/Totp.vue';

import { qrcode } from './services/dados';

export default {
    components: {
        Totp
    },
    data() {
        return {
            qrcode: ''
        };
    },
    methods: {
        paginacao() {
            qrcode()
                .then((resp) => {
                    // manipula o sucesso da requisição
                    console.log(resp);
                    this.qrcode = resp.data;
                })
                .catch((error) => {
                    // manipula erros da requisição
                    console.error(error);
                });
        },
    },
    mounted() {
        this.paginacao();
    }
};
</script>

<style lang="scss" >
* {
    padding: 0;
    margin: 0;
}

html,
body,
#app {
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;

    &>* {
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
    }
}
</style>
