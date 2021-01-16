using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.SignalR;
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
        private readonly IHubContext<NotificationsHub> _hubContext;

        public NotificationsController(
            INotificationService notificationsService, IHubContext<NotificationsHub> hubContext)
        {
            _notificationsService = notificationsService;
            _hubContext = hubContext;
        }

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

            if(NotificationsHub.UserConnectionsMap.TryGetValue(notification.Username, out var connectionId))
            {
                await _hubContext.Clients.Client(connectionId)
                    .SendAsync("NotificationReceived", notification.Content);
            }

            return new EmptyResult();
        }
    }
}
