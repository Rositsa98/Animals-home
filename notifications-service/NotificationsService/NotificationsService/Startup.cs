using AutoMapper;
using Dapper;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using NotificationsService.Entities;
using NotificationsService.Models;
using NotificationsService.Services;
using System;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Reflection;

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
            services.AddControllers();
            services.AddSignalR();
            services.AddTransient<INotificationService, NotificationService>();
            
            MapDbEntities();
            RegisterMappingConfiguration(services);         
        }

        private void MapDbEntities()
        {
            PropertyInfo ColumnAttributeMapper(Type type, string columnName) =>
                type.GetProperties().FirstOrDefault(prop =>
                    prop.GetCustomAttributes(false).OfType<ColumnAttribute>().Any(attr => attr.Name == columnName));

            SqlMapper.SetTypeMap(typeof(NotificationEntity),
                new CustomPropertyTypeMap(typeof(NotificationEntity), ColumnAttributeMapper));
        }

        private void RegisterMappingConfiguration(IServiceCollection services)
        {
            var configuration = new MapperConfiguration(cfg =>
            {
                cfg.CreateMap<NotificationEntity, Notification>();
            });

            var mapper = configuration.CreateMapper();
            services.AddSingleton(mapper);
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

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapHub<NotificationsHub>("/live");
                endpoints.MapControllers();
            });
        }
    }
}
