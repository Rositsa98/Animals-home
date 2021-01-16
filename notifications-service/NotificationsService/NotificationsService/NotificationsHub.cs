using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.SignalR;
using System;
using System.Collections.Concurrent;
using System.Threading.Tasks;

namespace NotificationsService
{
    [Authorize]
    public class NotificationsHub : Hub
    {
        public static ConcurrentDictionary<string, string> UserConnectionsMap = 
            new ConcurrentDictionary<string, string>();

        public static ConcurrentDictionary<string, string> ConnectionsUserMap =
    new ConcurrentDictionary<string, string>();

        public override async Task OnConnectedAsync()
        {
            await base.OnConnectedAsync();

            var username = Context.User.FindFirst("sub");
            UserConnectionsMap[username.Value] = Context.ConnectionId;
            ConnectionsUserMap[Context.ConnectionId] = username.Value;
        }

        public override async Task OnDisconnectedAsync(Exception exception)
        {
            await base.OnDisconnectedAsync(exception);
            if(ConnectionsUserMap.TryGetValue(Context.ConnectionId, out var username))
            {
                UserConnectionsMap.TryRemove(username, out _);
                ConnectionsUserMap.TryRemove(Context.ConnectionId, out _);
            }
        }
    }
}
