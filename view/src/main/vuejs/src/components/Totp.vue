<template>
    <br />
    <div style=" display: flex; align-items: center; justify-content: center;">
        <v-otp-input ref="otpInput" v-model:value="code" input-classes="otp-input" separator="-" :num-inputs="6"
            :should-auto-focus="true" input-type="letter-numeric" :placeholder="['*', '*', '*', '*', '*', '*']" />
    </div>
    <br />
    <h3>{{ msg }}</h3>
    <br />
    <Button label="Verify" @click="check()" />
    <br />
    <br />
    <Button label="Clear" @click="clear()" />
</template>

<script>
import Button from 'primevue/button';
import VOtpInput from "vue3-otp-input";

import { verify } from '../services/dados';

export default {
    name: 'TotpInput',
    components: {
        VOtpInput, Button
    },
    data() {
        return {
            code: '',
            msg: ''
        };
    },
    methods: {
        clear() {
            this.code = '';
            this.msg = '';
        },
        check() {
            let totp = {};
            totp.code = this.code;

            verify(totp)
                .then((resp) => {
                    // manipula o sucesso da requisição
                    console.log(resp);
                    if (resp.data) {
                        this.msg = 'TOTP válido';
                    } else {
                        this.msg = 'TOTP inválido';
                    }
                });
        }
    }
};
</script>

<style lang="scss" >
.otp-input {
    width: 40px;
    height: 40px;
    padding: 5px;
    margin: 0 10px;
    font-size: 20px;
    border-radius: 4px;
    border: 1px solid rgba(0, 0, 0, 0.3);
    text-align: center;
}

/* Background colour of an input field with value */
.otp-input.is-complete {
    background-color: #e4e4e4;
}

.otp-input::-webkit-inner-spin-button,
.otp-input::-webkit-outer-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

input::placeholder {
    font-size: 15px;
    text-align: center;
    font-weight: 600;
}
</style>
