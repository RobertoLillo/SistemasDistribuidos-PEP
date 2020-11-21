<template lang="">
    <v-app>
        <v-main>
            <AppHeader/>
            <v-container>
                <router-view/>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
import AppHeader from '../components/AppHeader'

export default {
    components: {
    AppHeader
  },
}
</script>