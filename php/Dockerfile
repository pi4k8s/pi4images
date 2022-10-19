FROM php:7.4-apache

RUN  cd /var/www/html && apt update \
     && apt install -y libwebp-dev libjpeg-dev libpng-dev libfreetype6-dev \
     && docker-php-source extract \
     && cd /usr/src/php/ext/gd \
     && docker-php-ext-configure gd --with-webp=/usr/include/webp --with-jpeg=/usr/include --with-freetype=/usr/include/freetype2 \
     make && make install \
     && docker-php-ext-install mysqli pdo pdo_mysql gd \
     && docker-php-ext-enable pdo_mysql \
     && apt clean && rm -rf /tmp/*