//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace AgenciaViagens.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Adquire
    {
        public int id_adq { get; set; }
        public int cliente { get; set; }
        public int viagem { get; set; }
        public Nullable<int> promocao { get; set; }
    
        public virtual Cliente Cliente1 { get; set; }
        public virtual Promocao Promocao1 { get; set; }
        public virtual Viagem Viagem1 { get; set; }
    }
}