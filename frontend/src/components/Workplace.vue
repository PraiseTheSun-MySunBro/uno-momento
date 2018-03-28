<template>
    <b-container class="work-place">
        <b-tabs>
            <b-tab title="Minu lõputööd" active>
                <b-list-group>
                    <div v-for="(lecturer, index) in lecturers" :key="index">
                        <b-list-group-item href="#" 
                                            class="flex-column align-items-start" 
                                            v-for="(thesis, index) in JSON.parse(lecturer.theses)" 
                                            :key="index">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">{{ thesis.ee_title}}</h5>
                            </div>
                            <p class="mb-1">{{ thesis.ee_description }}</p>
                        </b-list-group-item>
                    </div>     
                </b-list-group>
            </b-tab>
            <b-tab title="Tudengi pakkumised">
                <b-list-group>
                    <b-list-group-item href="#" 
                                        class="flex-column align-items-start" 
                                        v-for="(offer, index) in studentTheses" 
                                        v-if="lecturerName==offer.lecturer" 
                                        :key="index">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">{{ offer.ee_title}}</h5>
                        </div>
                        <p class="mb-1">{{ offer.ee_description }}</p>
                        <small class="text-muted">{{ studentName }}</small>
                    </b-list-group-item>
                </b-list-group>
            </b-tab>
        </b-tabs>
    </b-container>
</template>

<script>
    export default {
    data () {
        return {
            /* list of offered theses */
            studentTheses: [],
            /* list of lecturers  with list of theses */
            lecturers: []
        }
    },
    methods: {
        getPersons: function() {
        console.log("I'm working!")
        axios.get("api/curators")
            .then((response) => {
            console.log('Response', response)
            })
            .catch((error) => {
            console.error('Error', error)
            })
        }  
    },
    mounted: function() {
        axios.get('/api/curators')
            .then(response => {
                this.lecturers=response.data
            }).catch(e => {
                console.error(e.data)
            })
        }
    }
</script>

<style>

</style>