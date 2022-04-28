FROM haproxy:2.1
RUN mkdir /etc/certs/
COPY haproxy-app.cfg /usr/local/etc/haproxy/haproxy.cfg
COPY myCert.pem /etc/certs/myCert.pem
RUN mkdir /run/haproxy/