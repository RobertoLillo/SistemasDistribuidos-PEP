<template>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      v-model="user.name"
      :counter="30"
      :rules="nameRules"
      label="Nombre"
      required
    ></v-text-field>

    <v-text-field
      v-model="user.mail"
      :counter="30"
      :rules="emailRules"
      label="E-mail"
      required
    ></v-text-field>

    <v-text-field
      v-model="user.rut"
      :rules="[validateRUT()]"
      :hint="'Formato: 12345678-9'"
      label="RUT"
      required
    ></v-text-field>

    <v-select
      v-model="selectRegion"
      :items="region"
      :rules="[v => !!v || 'Ingrese su región']"
      label="Región"
      required
    ></v-select>

    <v-select
      :disabled=false
      v-model="selectComuna"
      :items="comunas"
      :rules="[v => !!v || 'Ingrese su comuna']"
      label="Comuna"
      required
    ></v-select>

    <v-text-field
      v-model="user.address"
      :counter="30"
      :rules="addressRules"
      label="Dirección"
      required
    ></v-text-field>

    <v-text-field
      v-model="user.reason"
      :counter="30"
      :rules="reasonRules"
      label="Motivo"
      required
    ></v-text-field>


    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="validate();saveUser();"
    >
      Confirmar Registro
    </v-btn>

    <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Limpiar Formulario
    </v-btn>

  </v-form>
</template>

<script>
import http from "@/http-common";
import ValidatorService from '../services/validatorsService';

  export default {
    data: () => ({
      valid: true,
      name: '',
      nameRules: [
        v => !!v || 'Debe ingresar su nombre',
        v => (v && v.length <= 30) || 'Nombre puede tener un largo máximo de 30 caracteres',
      ],
      email: '',
      emailRules: [
        v => !!v || 'Ingrese su E-mail',
        v => /.+@.+\..+/.test(v) || 'E-mail debe ser válido',
      ],
      rutRules: [
        (v) => validarRUT(v) || 'RUT inválido (Formato: 12345678-9)'
      ],
      reasonRules: [
        v => !!v || 'Debe ingresar el motivo de su permiso',
        v => (v && v.length <= 30) || 'Motivo puede tener un largo máximo de 30 caracteres',
      ],
      addressRules: [
        v => !!v || 'Debe ingresar su dirección',
        v => (v && v.length <= 30) || 'Dirección puede tener un largo máximo de 30 caracteres',
      ],
      selectRegion: null,
      selectComuna: null,
      items: [
        'Item 1',
        'Item 2',
        'Item 3',
        'Item 4',
      ],
      checkbox: false,
      user: {
        rut: '',
        name: '',
        address: '',
        reason: '',
        mail: '',
      },
      temporary: null,
      region: ["Región Metropolitana de Santiago"],
      comunas: ["Cerrillos", "Cerro Navia", "Conchalí", "El Bosque", "Estación Central", "Huechuraba", "Independencia", "La Cisterna", "La Florida", "La Granja", "La Pintana", "La Reina", "Las Condes", "Lo Barnechea", "Lo Espejo", "Lo Prado", "Macul", "Maipú", "Ñuñoa", "Pedro Aguirre Cerda", "Peñalolén", "Providencia", "Pudahuel", "Quilicura", "Quinta Normal", "Recoleta", "Renca", "Santiago", "San Joaquín", "San Miguel", "San Ramón", "Vitacura", "Puente Alto", "Pirque", "San José de Maipo", "Colina", "Lampa", "Tiltil", "San Bernardo", "Buin", "Calera de Tango", "Paine", "Melipilla", "Alhué", "Curacaví", "María Pinto", "San Pedro", "Talagante", "El Monte", "Isla de Maipo", "Padre Hurtado", "Peñaflor"]
    }),

    methods: {
      validate () {
        this.$refs.form.validate()
      },
      reset () {
        this.$refs.form.reset()
      },
      async saveUser() {
        var user = {
            rut: this.user.rut,
            name: this.user.name,
            address: this.user.address + ', ' + this.selectComuna + ', ' + this.selectRegion,
            reason: this.user.reason,
            mail: this.user.mail
        };
        http
            .post("/api/newTemporaryPermit", user)
            .then(response => {
            console.log(response.data);
            this.temporary = response.data;
            alert(this.temporary.name +"se ha generado tu permiso con éxito \n"+
            "ID: "+ this.temporary.id+"\n"+
            "Válido desde: " + this.temporary.validFrom +"\n"+
            "Válido hasta: " + this.temporary.validUntil)
            })
            .catch(e => {
            console.log(e);
            alert("Registro Fallido");
            });
      },
      validateRUT: function () {
        return ValidatorService.validateRUT();
      },
    }
  }
</script>