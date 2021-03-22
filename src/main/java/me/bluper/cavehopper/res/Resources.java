package me.bluper.cavehopper.res;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Resources {
	private static void collectURL(ResourceURLFilter f, Set<URL> s, URL u)
	{
		if (f == null || f.accept(u)) s.add(u);
	}

	private static void iterateFiles(File file, ResourceURLFilter filter, Set<URL> set) throws MalformedURLException, IOException
	{
		for(File f : file.listFiles())
		{
			if (f.isDirectory()) iterateFiles(f, filter, set);
			else if(f.isFile()) collectURL(filter, set, f.toURI().toURL());
		}
	}

	private static void iterateJarFiles(File file, ResourceURLFilter filter, Set<URL> set) throws MalformedURLException, IOException
	{
		@SuppressWarnings("resource")
		JarFile jarFile = new JarFile(file);
		for(Enumeration<JarEntry> jarEntry = jarFile.entries(); jarEntry.hasMoreElements();)
		{
			JarEntry j = jarEntry.nextElement();
			if (!j.isDirectory()) {
				collectURL(filter, set, new URL("jar", "", file.toURI() + "!/" + j.getName()));
			}
		}
	}


	private static void iterateEntry(File file, ResourceURLFilter filter, Set<URL> set) throws MalformedURLException, IOException
	{
		if (file.isDirectory()) iterateFiles(file, filter, set);
		else if (file.isFile() && file.getName().toLowerCase().endsWith(".jar")) iterateJarFiles(file, filter, set);
	}


	public static Set<URL> getResourceURLs() throws IOException, URISyntaxException
	{
		return getResourceURLs((ResourceURLFilter)null);
	}

	public static Set<URL> getResourceURLs(@SuppressWarnings("rawtypes") Class rootClass) throws IOException, URISyntaxException
	{
		return getResourceURLs(rootClass, (ResourceURLFilter)null);
	}

	public static Set<URL> getResourceURLs(ResourceURLFilter filter) throws IOException, URISyntaxException
	{
		Set<URL> collectedURLs = new HashSet<>();
		URLClassLoader ucl = (URLClassLoader)ClassLoader.getSystemClassLoader();
		for (URL url: ucl.getURLs())
		{
			iterateEntry(new File(url.toURI()), filter, collectedURLs);
		}
		return collectedURLs;
	}

	public static Set<URL> getResourceURLs(@SuppressWarnings("rawtypes") Class rootClass, ResourceURLFilter filter) throws IOException, URISyntaxException
	{
		Set<URL> collectedURLs = new HashSet<>();
		CodeSource src = rootClass.getProtectionDomain().getCodeSource();
		iterateEntry(new File(src.getLocation().toURI()), filter, collectedURLs);
		return collectedURLs;
	}
}
