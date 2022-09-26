using System.Linq;
using System.Web.Mvc;
using AgenciaViagens.Models;

namespace AgenciaViagens.Controllers
{
    public class DestinosController : Controller
    {
        private AgenciaViagensEntities db = new AgenciaViagensEntities();

        // rota: Destinos, lista os destinos
        public ActionResult Index(int? id)//id_promo
        {
            if (id != null)
            {
                ViewBag.promocao = id;
            }
            return View(db.Destino.ToList());
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}