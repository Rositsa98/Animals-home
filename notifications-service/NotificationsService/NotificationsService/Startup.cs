using Dapper;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.IdentityModel.Logging;
using Microsoft.IdentityModel.Tokens;
using NotificationsService.Entities;
using NotificationsService.Services;
using System;
using System.ComponentModel.DataAnnotations.Schema;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace NotificationsService
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            IdentityModelEventSource.ShowPII = true;

            services.AddControllers();
            services.AddSignalR();
            services.AddAuthentication(options => {                
                options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
                options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;

            }).AddJwtBearer(ConfigureJwtBearerOptions);

            services.AddTransient<INotificationService, NotificationService>();
            MapDbEntities();
        }

        private void ConfigureJwtBearerOptions(JwtBearerOptions options)
        {           
            var key = Configuration.GetValue<string>("JwtSymmetricKey");

            var jwtHandler = new JwtSecurityTokenHandler();
            jwtHandler.InboundClaimTypeMap.Clear();

            options.SecurityTokenValidators.Clear();
            options.SecurityTokenValidators.Add(jwtHandler);

            options.TokenValidationParameters = new TokenValidationParameters
            {
                ValidAudience = "notifications-service",
                IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(key)),
                RequireSignedTokens = true,
                RequireExpirationTime = true,
                ValidateLifetime = true,
                ValidateIssuer = false,
                ValidateIssuerSigningKey = true
            };
        }

        private void MapDbEntities()
        {
            PropertyInfo ColumnAttributeMapper(Type type, string columnName) =>
                type.GetProperties().FirstOrDefault(prop =>
                    prop.GetCustomAttributes(false).OfType<ColumnAttribute>().Any(attr => attr.Name == columnName));

            SqlMapper.SetTypeMap(typeof(NotificationEntity),
                new CustomPropertyTypeMap(typeof(NotificationEntity), ColumnAttributeMapper));
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthentication();
            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapHub<NotificationsHub>("/live");
                endpoints.MapControllers();
            });
        }
    }
}
