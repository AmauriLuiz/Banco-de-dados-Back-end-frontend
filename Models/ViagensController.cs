using System.Data;
using System.Linq;
using System.Net;
using System.Web.Mvc;
using AgenciaViagens.Models;

namespace AgenciaViagens.Controllers
{
    public class ViagensController : Controller
    {
        private AgenciaViagensEntities db = new AgenciaViagensEntities();

        // GET: Viagem
        // rota: Viagens/5/2, lista as viagens para o destino selecionado (e promoção, opcional)
        public ActionResult Index(int id, int? id2)//id_dest, id_promo
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            IQueryable<Viagem> viagem = db.Viagem.Where(v => v.destino == id);
            ViewBag.promocao = null;
            if (id2 != null)
            {
                Promocao promocao = db.Promocao.Find(id2);
                ViewBag.promocao = promocao;
                viagem = viagem.Where(v => v.partida <= promocao.vencimento);
            }
            return View(viagem.ToList());
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