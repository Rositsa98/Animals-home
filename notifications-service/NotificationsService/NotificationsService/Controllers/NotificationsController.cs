using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using NotificationsService.Models;
using NotificationsService.Services;
using System.Threading.Tasks;

namespace NotificationsService.Controllers
{
    [ApiController]
    [Authorize]
    [Route("[controller]")]
    public class NotificationsController : ControllerBase
    {
        private readonly INotificationService _notificationsService;

        public NotificationsController(INotificationService notificationsService)
            => _notificationsService = notificationsService;


        [HttpGet("Get/{username}")]
        public async Task<IActionResult> Get(string username)
        {
            var notifications = await _notificationsService.GetAsync(username);
            return new JsonResult(notifications);
        }

        [HttpPost("Add")]
        [Consumes("application/json")]
        public async Task<IActionResult> Add([FromBody] AddNotificationModel notification)
        {
            await _notificationsService.AddAsync(notification);
            return new EmptyResult();
        }
    }
}
