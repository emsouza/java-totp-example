import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import eslintPlugin from "vite-plugin-eslint";

export default ({ command }) => {
    const build = command === "build";
    const serve = command === "serve";

    if (serve) {
        const config = {
            plugins: [
                vue(),
                eslintPlugin({ cache: false })
            ],
            server: {
                proxy: {
                    "/api": "http://localhost:8080/java-totp",
                },
            }
        };

        return defineConfig(config);
    }

    if (build) {
        const config = {
            plugins: [
                vue(),
            ],
            base: "./",
            build: {
                emptyOutDir: true,
                outDir: "../../../target/java-totp-view",
            }
        };

        return defineConfig(config);
    }
};