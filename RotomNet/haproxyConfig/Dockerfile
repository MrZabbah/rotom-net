FROM haproxy:2.1
COPY haproxy-app.cfg /usr/local/etc/haproxy/haproxy.cfg
RUN mkdir etc/certs
COPY myCert.pem /etc/certs/myCert.pem