using Microsoft.AspNetCore.SignalR;
using System.Threading.Tasks;

namespace NotificationsService
{
    public class NotificationsHub : Hub
    {
        public async Task SendNotification(string contents)
        {
            await Clients.All.SendAsync("NotificationReceived", contents);
        }
    }
}
