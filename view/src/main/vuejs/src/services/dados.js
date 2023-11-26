import axios from 'axios';

export function http() {
    return axios.create();
}

export function qrcode() {
    return http().get("api/totp");
}

export function verify(data) {
    return http().post("api/totp", data);
}