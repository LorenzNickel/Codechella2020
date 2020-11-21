import Vue from 'vue';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: "#005670",
                button: "#4298b5",
                button2: "#0085ad",
                button3: "#8db9ca",
            }
        }
    },
    icons: {
        iconfont: "mdi"
    }
});
