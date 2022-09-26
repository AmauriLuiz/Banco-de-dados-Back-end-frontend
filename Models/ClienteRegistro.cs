using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AgenciaViagens.Models
{
    public class ClienteRegistro
    {
        public Cliente cliente { get; set; }
        public RegisterViewModel registro { get; set; }
    }
}