function validarRUT(rut) {
    var Fn = {
      // Valida el rut con su cadena completa "XXXXXXXX-X"
      validaRut: function (rutCompleto) {
        if (!/^[0-9]+[-|‐]{1}[0-9kK]{1}$/.test(rutCompleto))
          return false;
        var tmp = rutCompleto.split('-');
        var digv = tmp[1];
        var rut = tmp[0];
        if (digv == 'K') digv = 'k';
        return (Fn.dv(rut) == digv);
      },
      dv: function (T) {
        var M = 0, S = 1;
        for (; T; T = Math.floor(T / 10))
          S = (S + T % 10 * (9 - M++ % 6)) % 11;
        return S ? S - 1 : 'k';
      }
    }
    return Fn.validaRut(rut);
  }
  
  export default {
    required: function (propertyType) {
      return (v) =>
        (v && v.length > 0) ||
        `Campo ${propertyType} es requerido`
    },
    requiredYear: function (propertyType) {
      return (v) =>
        (v && v > 0) ||
        `Campo ${propertyType} es requerido`
    },
    validateEmail: function () {
      const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return (v) =>
        pattern.test(v) ||
        'Correo debe ser válido';
    },
    validateRUT: function () {
      return (v) => validarRUT(v) || 'RUT inválido (Formato: 12345678-9)';
    },
    rut: function (rutCompleto) {
      
      const dv = (T) => {
        var M = 0, S = 1;
        for (; T; T = Math.floor(T / 10))
          S = (S + T % 10 * (9 - M++ % 6)) % 11;
        return S ? S - 1 : 'k';
      }
      if (!/^[0-9]+[-|‐]{1}[0-9kK]{1}$/.test(rutCompleto))
        return false;
      var tmp = rutCompleto.split('-');
      var digv = tmp[1];
      var rut = tmp[0];
      if (digv == 'K') digv = 'k';
      return (dv(rut) == digv);
    },
    validatePassword: function () {
      const pattern = new RegExp("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*](?=\\S+$).{8,32}$");
      return (v) =>
        (v && v.length >= 8 && pattern.test(v)) ||
        'La contraseña no cumple con los requisitos mínimos de seguridad.'
    }
  }