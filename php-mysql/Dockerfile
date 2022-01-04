FROM php:7.2-apache

RUN  cd /var/www/html && apt update \
     && docker-php-ext-install mysqli pdo pdo_mysql \
     && docker-php-ext-enable pdo_mysql \
     && apt clean && rm -rf /tmp/*