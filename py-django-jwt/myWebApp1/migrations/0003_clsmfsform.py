# Generated by Django 4.2 on 2023-05-03 18:33

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myWebApp1', '0002_rename_mymodel1_mymfmodel1'),
    ]

    operations = [
        migrations.CreateModel(
            name='clsMFSForm',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('title', models.CharField(max_length=200)),
                ('description', models.TextField()),
            ],
        ),
    ]
