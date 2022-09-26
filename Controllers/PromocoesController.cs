using System.Linq;
using System.Net;
using System.Web.Mvc;
using AgenciaViagens.Models;

namespace AgenciaViagens.Controllers
{
    public class PromocoesController : Controller
    {
        private AgenciaViagensEntities db = new AgenciaViagensEntities();

        // rota: Promocoes, lista as promoções
        public ActionResult Index()
        {
            return View(db.Promocao.ToList());
        }

        // GET: Promocoes
        public ActionResult Redirect(int id)//id_promo
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Promocao promocao = db.Promocao.Find(id);
            if (promocao == null)
            {
                return HttpNotFound();
            }
            if (promocao.destino == null)
            {
                return RedirectToAction("Index", "Destinos", new { id = promocao.id_promo });
            }
            return RedirectToAction("Index", "Viagens", new { id = promocao.destino, id2 = promocao.id_promo });
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