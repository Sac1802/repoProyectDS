package com.example.Proyect_Tequilas_Ardientes;

import com.example.Proyect_Tequilas_Ardientes.Controller.*;
import com.example.Proyect_Tequilas_Ardientes.Model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class ProyectTequilasArdientesApplication {
	private static LoginController loginController;
	private static ProductController productController;
	private static InformationController informationController;
	private static UserController userController;
	private static MethodPayController methodPayController;

	private static ReceiptController receiptController;
	private static PaymentHistory paymentHistory;
	private static PaymentHistoryController paymentHistoryController;
    public ProyectTequilasArdientesApplication(LoginController loginController,
											   ProductController productController,
											   InformationController informationController,
											   UserController userController,
											   MethodPayController methodPayController,
											   ReceiptController receiptController,
											   PaymentHistoryController paymentHistoryController) {
        this.loginController = loginController;
		this.productController = productController;
		this.informationController = informationController;
		this.userController = userController;
		this.methodPayController = methodPayController;
		this.receiptController = receiptController;
		this.paymentHistoryController = paymentHistoryController;
    }
	static Scanner scanner = new Scanner(System.in);
	static User user = new User();
	static Information information = new Information();
	static MethodPay methodPay = new MethodPay();
	static Product product = new Product();
	static Receipt receipt = new Receipt();
	static PaymentHistory paymentHistoryEntity = new PaymentHistory();
	static Long idUser;
	static Long idInformation;
	static List<Product> productAddCarrito = new ArrayList<>();
	static List<Integer> cantidadProduct = new ArrayList<>();
	static String nameUser;

	static Optional<Information> optionInformation;
    public static void main(String[] args){
		SpringApplication.run(ProyectTequilasArdientesApplication.class, args);
		clearConsole();
		int num1 = 0;
		int numVendedor = 0;
		while (num1 == 0){
			int eleccion = bienvenida();
			int numPayment = 0;
			if (eleccion == 1) {
                String logined = login();
                String[] partes = logined.split(",");
                String role = partes[0];
                idUser = Long.parseLong(partes[1]);
                boolean entradaValida;
                if (role.equals("comprador")) {
                    optionInformation =
                            Optional.ofNullable(informationController.getInformationId(idUser));
                    if (optionInformation.isPresent()) {
                        idInformation = optionInformation.get().getId();
                        clearConsole();
                        int catalogoEleccion = catalogo("main");
                        while (catalogoEleccion != 5) {
                            switch (catalogoEleccion) {
                                case 1:
                                    if (productAddCarrito.size() != 0) {
                                        while (numPayment == 0) {
                                            catalogo("");
                                            System.out.println("1) Agregar otro producto al carrito");
                                            System.out.println("2) Comprar carrito");
                                            System.out.println("3) salir");
                                            System.out.print("Escriba su eleccion aqui: ");
                                            int eleccionCarrito = scanner.nextInt();
                                            switch (eleccionCarrito) {
                                                case 1:
                                                    carrito();
                                                    break;
                                                case 2:
                                                    Optional<MethodPay> optionalPay =
                                                            Optional.ofNullable(methodPayController.getId(idInformation));
                                                    if (optionalPay.isPresent()) {
                                                        comprar();
                                                    } else {
                                                        saveMethodPay("save");
                                                        comprar();
                                                    }
                                                    break;
                                                case 3:
                                                    numPayment = 1;
                                                    break;
                                            }
                                        }
                                    } else {
                                        if (numPayment == 0) {
                                            carrito();
                                        }
                                    }
                                    break;
                                case 2:
                                    information("update");
                                    catalogo("main");
                                    break;

                                case 3:
                                    System.out.print("Ingrese su correo: ");
                                    String email = scanner.next();
                                    userController.deleteUser(email);
                                    System.exit(0);
                                case 4:
                                    System.out.println("Cerrando sesión");
                                    break;
                                default:
                                    System.out.println("Por favor ingrese un valor valido");
                            }
                        }
                    } else {
                        clearConsole();
                        information("save");
                    }
                } else if (role.equals("vendedor")) {
                    Optional<Information> optionInformation =
                            Optional.ofNullable(informationController.getInformationId(idUser));
                    if (optionInformation.isPresent()) {
                        nameUser = informationController.getInformationId(idUser).getName();
                        List<Product> productUser =
                                productController.getProductUser(nameUser);
                        clearConsole();
                        while (numVendedor == 0) {
                            int option = 0;
                            entradaValida = false;
                            while (!entradaValida) {
                                try {
                                    option = bienvenidaVendedor(productUser);
                                    entradaValida = true;
                                } catch (InputMismatchException e) {
                                    System.out.println("Entrada inválida. Por favor, ingresa solo números enteros.");
                                    scanner.next();
                                }
                            }
                            switch (option) {
                                case 1:
                                    productSaveOrUpdate("save", 1);
                                    break;
                                case 2:
                                    productSaveOrUpdate("update", 2);
                                    break;
                                case 3:
                                    System.out.print("Ingrese el id del producto a eliminar: ");
                                    Long deleteProduct = scanner.nextLong();
                                    productController.deleteProduct(deleteProduct);
                                    break;
                                case 4:
                                    deleteVendedor(productUser);
                                    break;
                                case 5:
                                    information("update");
                                    break;
                                case 6:
                                    numVendedor = 1;
                                    clearConsole();
                                    break;
                            }
                        }
                    } else {
                        clearConsole();
                        information("save");
                    }
                }
				else if (role.equals("admin")) {
                    int numWhile = 0;
                    while (numWhile == 0) {
						entradaValida = false;
                        int adminElection = 0;
						while (!entradaValida) {
							try {
								adminElection = bienvenidaAdmin();
								entradaValida = true;
							} catch (InputMismatchException e) {
								System.out.println("Entrada inválida. Por favor, ingresa solo números enteros.");
								scanner.next();
							}
						}
                        if (adminElection == 1) {
                            int usuarioEleccion = adminUser();
                            switch (usuarioEleccion) {
                                case 1:
                                    registro("save");
                                    break;
                                case 2:
                                    information("Save");
                                    break;
                                case 3:
                                    registro("update");
                                    break;
                                case 4:
                                    information("update");
                                    break;
                                case 5:
                                    saveMethodPay("save");
                                    break;
                                case 6:
                                    saveMethodPay("update");
                                    break;
                                case 7:
                                    System.out.print("Ingrese el email del usuario: ");
                                    String emailUser = scanner.next();
									User userAdmin = userController.getId(emailUser).getBody();
									System.out.println("Busqueda");
									getUser(userAdmin);
                                    break;
                                case 8:
                                    System.out.print("Ingrese el correo: ");
                                    String email = scanner.next();
                                    userController.deleteUser(email);
                                    break;
                                default:
                                    System.out.println("Ingrese un valor valido");
                            }
                        } else if (adminElection == 2) {
                            System.out.println("==================================");
                            System.out.println("Administrar Productos");
                            System.out.println("===================================" + "\n");
                            System.out.println("1) Registrar nuevo producto");
                            System.out.println("2) editar producto");
                            System.out.println("3) eliminar producto");
                            System.out.println("4) Mostrar todos los productos");
                            System.out.print("Seleccione una opcion: ");
                            int electionProduct = scanner.nextInt();
                            switch (electionProduct) {
                                case 1:
                                    productSaveOrUpdate("save", 1);
                                    break;
                                case 2:
                                    productSaveOrUpdate("update", 2);
                                    break;
                                case 3:
                                    System.out.println("Ingrese el Id del producto a eliminar: ");
                                    Long idDeleteProduct = scanner.nextLong();
                                    productController.deleteProduct(idDeleteProduct);
									break;
                                case 4:
                                    List<Product> getProduct = productController.getProduct();
                                    myProducts(getProduct);
									break;
								default:
									System.out.println("Ingrese un valor valido");
                            }
                        } else if (adminElection == 3) {
                            numWhile = 1;
                        }
                    }
                }
            }else if (eleccion == 2){
				registro("save");
			}
		}
	}
// Esta parte comienza funciones comprador
	public static int bienvenida(){
		System.out.println("==================================");
		System.out.println("Sistema de Venta de bedidas");
		System.out.println("===================================");
		System.out.println("1) Iniciar Sesión.");
		System.out.println("2) Registarse.");
		System.out.print("Digite solamente el numero: ");
		int eleccion = scanner.nextInt();
		return eleccion;
	}

	public static int catalogo(String tipe){
		System.out.println("==================================");
		System.out.println("Catalogo");
		System.out.println("===================================");
		List<Product> getProduct = productController.getProduct();
		myProducts(getProduct);
		int eleccion2 = 0;
		if (tipe.equals("main")){
			System.out.println("1) Agregar Al carrito");
			System.out.println("2) Actualizar Informacion");
			System.out.println("3) Eliminar Cuenta");
			System.out.println("4) Salir");
			System.out.print("Por favor ingrese un numero: ");
			eleccion2 = scanner.nextInt();
		}
		return eleccion2;
	}

	public static void registro(String tipe){
		System.out.println("==================================");
		System.out.println("Registrarse");
		System.out.println("===================================");
		System.out.print("Email: ");
		String email = scanner.next();
		System.out.print("Password: ");
		String password = scanner.next();
		System.out.print("Role: ");
		String role = scanner.next();
		if (tipe.equals("save")){
			user.setEmail(email);
			user.setPassword(password);
			System.out.print("Confirm Password: ");
			String confirmPassword = scanner.next();
			user.setConfirmPassword(confirmPassword);
			user.setRole(role);
			userController.saveUser(user);
		} else if (tipe.equals("update")) {
			user.setEmail(email);
			user.setRole(role);
			user.setPassword(password);
			System.out.print("Ingrese el Id del usuario a actualizar: ");
			Long idUpdateUser = scanner.nextLong();
			userController.updateUser(user, idUpdateUser);
		}
	}

	public static String login(){
		String logined;
		System.out.println("==================================");
		System.out.println("Iniciar Sesión");
		System.out.println("===================================");
		System.out.print("Digite Su Corre Electronico: ");
		String email = scanner.next();
		System.out.print("Introcuce la contraseña: ");
		String password = scanner.next();
		user.setPassword(password);
		user.setEmail(email);
		logined = loginController.login(user);
		return logined;
	}

	public static void information(String tipe){
		System.out.println("por favor ingrese los siguientes datos");
		System.out.print("Nombre: ");
		String name = scanner.next();
		System.out.print("Apellido: ");
		String lastname = scanner.next();
		System.out.print("Telefono: ");
		String cellphone = scanner.next();
		System.out.print("Ciudad: ");
		String city = scanner.next();
		System.out.print("Pais: ");
		String country = scanner.next();
		System.out.print("Direccion: ");
		String address = scanner.next();
		if (tipe.equals("update")){
			Information updateInfo = informationController.getInformationId(idUser);
			System.out.println(updateInfo);
			updateInfo.setName(name);
			updateInfo.setLast_name(lastname);
			updateInfo.setCellphone(cellphone);
			updateInfo.setCity(city);
			updateInfo.setCountry(country);
			updateInfo.setAddress(address);
			informationController.updateInformation(idInformation, updateInfo);
		}if (tipe.equals("save")){
			information.setName(name);
			information.setLast_name(lastname);
			information.setCellphone(cellphone);
			information.setCity(city);
			information.setCountry(country);
			information.setAddress(address);
			information.setIdUser(idUser);
			informationController.saveInformation(information);
		}
	}

	public static void carrito(){
		System.out.print("Por Favor ingrese el id del producto a agregar " +
				"al carrito: ");
		Long idProductCarrito = scanner.nextLong();
		Product addProduct = productController.findById(idProductCarrito);
		productAddCarrito.addLast(addProduct);
		System.out.print("Ingrese la cantidad de productos a comprar: ");
		Integer cantidad = scanner.nextInt();
		cantidadProduct.addLast(cantidad);
	}

	public static String saveMethodPay(String tipe){
		String response;
		try {
			System.out.println("Por favor ingrese estos valores");
			System.out.print("Numero de tarjeta: ");
			String numero = scanner.next();
			System.out.println("Por favor ingrese la fecha en el formato dd-MM-yyyy:");
			System.out.print("Feche de Expiración: ");
			String fecha = scanner.next();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			try {
				date = format.parse(fecha);
				response = "ok";
			} catch (Exception e) {
				System.out.println("Formato de fecha inválido. Por favor ingrese la fecha en el formato dd-MM-yyyy.");
				response = "bad";
			}
			
			System.out.print("Ingrese el CVV: ");
			String cvv = scanner.next();
			
			if (tipe.equals("save")){
				methodPay.setNumber(numero);
				methodPay.setDate(date);
				methodPay.setCvv(cvv);
				methodPay.setIdInformation(idInformation);
				methodPayController.saveMethodPay(methodPay);
			} else if (tipe.equals("update")) {
				Optional<MethodPay> optionalMethodPay = Optional.ofNullable
						(methodPayController.getId(idInformation));
				if (optionalMethodPay.isPresent()){
					MethodPay updateMethod = optionalMethodPay.get();
					updateMethod.setNumber(numero);
					updateMethod.setDate(date);
					updateMethod.setCvv(cvv);
					updateMethod.setIdInformation(idInformation);
					methodPayController.updateMethodPay(updateMethod.getId(), methodPay);
				}else {
					response = "bad";
				}
			}

		}catch (Exception e){
			response = "bad";
		}
		return response;
	}

	public static void comprar(){
		myProducts(productAddCarrito);
		System.out.print("Desea comprar estos articulos?: ");
		String buyElection = scanner.next();
		if (buyElection.equals("si")){
			System.out.println("===============================");
			System.out.println("Productos Comprados!!");
			System.out.println("===============================" + "\n");
			float total = 0;
			for (Product price: productAddCarrito){
				float priceSum = price.getPrice();
				int multi = 0;
				for (int cantidadProductBuy: cantidadProduct){
					multi = cantidadProductBuy;
					int actualStock = price.getStock();
					int newStock = actualStock - cantidadProductBuy;
					Long idProductStock = price.getId();
					productController.updateStockProduct(newStock, idProductStock);
				}
				String nameUserReceipt = optionInformation.get().getName();
				String nameProductReceipt = price.getNameProduct();
				total += priceSum * multi;
				LocalDateTime dateTime = LocalDateTime.now();
				receipt.setNameUser(nameUserReceipt);
				receipt.setNameProduct(nameProductReceipt);
				receipt.setTotal(total);
				receipt.setDateBuy(dateTime);
				Receipt saveTotal = receiptController.saveReceipt(receipt)
						.getBody();
				paymentHistoryEntity.setIdUser(idInformation);
				paymentHistoryEntity.setIdReceipt(saveTotal.getId());
				paymentHistoryController.savePaymentHistory(paymentHistoryEntity);
			}
			myProducts(productAddCarrito);
			productAddCarrito.clear();
			System.out.println("El total fue: " + total);

		}
	}

	//comienzan funciones vendedor

	public static int bienvenidaVendedor(List<Product> productUser){
		System.out.println("==================================");
		System.out.println("Bienvenido Vendedor");
		System.out.println("===================================" + "\n");
		System.out.println("Tus Productos");
		myProducts(productUser);
		System.out.println("1) Registrar nuevo producto");
		System.out.println("2) Actualizar producto");
		System.out.println("3) eliminar producto");
		System.out.println("4) eliminar cuenta");
		System.out.println("5) Actualizar Informacion");
		System.out.println("6) Salir");
		System.out.print("Seleccione un opcion: ");
		int option = scanner.nextInt();
		return option;
	}

	public static void productSaveOrUpdate(String tipe, int numEleccion){
		System.out.println("Ingrese los siguientes valores");
		System.out.print("Ingrese el nombre del producto: ");
		String nameProduct = scanner.next();
		System.out.print("ingrese el precio unitario: ");
		float price = scanner.nextFloat();
		System.out.print("Ingrese la cantidad de producto " +
				"en stock que tiene: ");
		int stock = scanner.nextInt();
		System.out.print("Describa una descripcion breve del producto: ");
		String describe = scanner.next();
		if (tipe.equals("update") && numEleccion == 2){
			System.out.print("Ingrese el id del producto: ");
			Long productIdAdmin = scanner.nextLong();
			System.out.print("Ingresa el nombre del vendedor: ");
			String nameSeller = scanner.next();
			product.setNameProduct(nameProduct);
			product.setPrice(price);
			product.setStock(stock);
			product.setDescription(describe);
			product.setSeller(nameSeller);
			productController.updateProduct(productIdAdmin, product);
		}if (tipe.equals("save") && numEleccion == 1){
			product.setNameProduct(nameProduct);
			product.setPrice(price);
			product.setStock(stock);
			product.setDescription(describe);
			product.setSeller(nameUser);
			System.out.println(nameUser);
			productController.saveProduct(product);
		}
	}

	public static void myProducts(List<Product> productUser){
		for (Product text: productUser){
			System.out.println("Id: " + text.getId());
			System.out.println("Nombre del producto: " + text.getNameProduct());
			System.out.println("Precio del Producto: " + text.getPrice());
			System.out.println("Stock del producto: " + text.getStock());
			System.out.println("Descripcion del producto: " +text.getDescription()
					+ "\n");
		}
	}

	public static void deleteVendedor(List<Product> productUser){
		System.out.println("Desea Eliminar su cuenta?");
		System.out.println("Tambien se eliminaran los productos" +
				"guardados");
		System.out.print("Su respuesta: ");
		String response = scanner.next();
		if (response.equals("si")){
			userController.deleteById(idUser);
			for (Product text: productUser){
				Long idProductDelete = text.getId();
				productController.deleteProduct(idProductDelete);
			}
		}else {

		}
	}

	//funciones para admin

	public static int bienvenidaAdmin(){
		System.out.println("==================================");
		System.out.println("Bienvenido admin");
		System.out.println("===================================" + "\n");
		System.out.println("1) Administrar Usuarios");
		System.out.println("2) Administrar Productos");
		System.out.println("3) Salir");
		System.out.print("Escriba su eleccion: ");
		int adminElection = scanner.nextInt();
		return adminElection;
	}

	public static int adminUser(){
		System.out.println("==================================");
		System.out.println("Administrar Usuario");
		System.out.println("===================================" + "\n");
		System.out.println("1) Registrar nuevo Usuario");
		System.out.println("2) Registrar Informacion");
		System.out.println("3) Actualizar Usuario");
		System.out.println("4) Actualizar Informacion");
		System.out.println("5) Registrar nuevo metodo de pago");
		System.out.println("6) Actualizar metodo de pago");
		System.out.println("7) Buscar un usuario");
		System.out.println("8) Eliminar Usuario" + "\n");
		System.out.print("Ingrese la opcion: ");
		int usuarioEleccion = scanner.nextInt();
		return usuarioEleccion;
	}

	public static void clearConsole(){
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public static void getUser(User user){
		System.out.println("Id: " + user.getId());
		System.out.println("Email: " + user.getEmail());
		System.out.println("Role: " + user.getRole());
	}
}
