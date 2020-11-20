import Vue from 'vue';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: "#005670",
                button: "#4298b5"
            }
        }
    },
    icons: {
        iconfont: "mdi"
    }
});
