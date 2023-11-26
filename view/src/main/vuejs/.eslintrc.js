module.exports = {
    root: true,
    env: {
        node: true,
    },
    parser: "vue-eslint-parser",
    extends: [
        // https://github.com/vuejs/eslint-plugin-vue
        'eslint:recommended',
        'plugin:vue/vue3-essential',
        "prettier"
    ],
    'rules': {
        // override/add rules settings here, such as:
        // 'vue/no-unused-vars': 'error'
        'vue/no-reserved-component-names': 'off'

    },
}
